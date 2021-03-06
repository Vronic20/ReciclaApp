package upc.pe.recycleappupc

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toFile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import upc.pe.recycleappupc.camera.ConstantsCamera
import upc.pe.recycleappupc.camera.ConstantsCamera.TAG
import upc.pe.recycleappupc.databinding.ActivityCameraBinding
import upc.pe.recycleappupc.models.Label
import upc.pe.recycleappupc.network.ApiRekognition
import upc.pe.recycleappupc.services.LabelService
import java.io.File
import java.io.FileOutputStream
import java.net.URI
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.collections.ArrayList


class CameraActivity : AppCompatActivity() {

    private val IMAGE_GALLERY_REQUEST_CODE: Int = 2001
    private lateinit var binding: ActivityCameraBinding
    private val REQUEST_CAMERA = 100
    private var imageCapture:ImageCapture? = null
    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor:ExecutorService
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),REQUEST_CAMERA)

        outputDirectory = getOutputDirectory()
        cameraExecutor = Executors.newSingleThreadExecutor()
        if(allPermissionGranted()){
            startCamera()
        }else{
            ActivityCompat.requestPermissions(this,
                ConstantsCamera.REQUIRED_PERMISSION_CAMERA,
                ConstantsCamera.REQUEST_CAMERA)
        }

        binding.btnAdvices.setOnClickListener{
            //it.findNavController().navigate(R.id.advicesFragment2)
            startActivity(Intent(baseContext, AdviceActivity::class.java))
        }

        binding.btnCamara.setOnClickListener{
            takePhoto()
        }

        val getImage = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                val photo = uriToFile(it,"nombreTemporal");
                getLabels(photo!!)
                val message = "Foto Obtenida de "
                Toast.makeText(baseContext,"$message $it", Toast.LENGTH_SHORT).show()
            }
        )
        binding.btnGallery.setOnClickListener {
            getImage.launch("image/*")
        }

    }

    private fun uriToFile(uri: Uri, fileName: String): File? {
        this.contentResolver.openInputStream(uri)?.let {
                inputStream ->
            // Create File
            val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val tempFile = File.createTempFile(fileName, ".jpg", storageDir)
            // Other
            val outputStream = FileOutputStream(tempFile)
            inputStream.copyTo(outputStream)
            inputStream.close()
            outputStream.close()
            return tempFile
        }
        return null
    }

    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let { mFile -> File(mFile,resources.getString(R.string.app_name)).apply {
            mkdirs()
        } }

        return if(mediaDir != null && mediaDir.exists())
            mediaDir else filesDir
    }

    private fun takePhoto()  {
        val imageCaptured = imageCapture ?: return
        val photoFile = File(
            outputDirectory, SimpleDateFormat(ConstantsCamera
                .FILE_NAME_FORMAT, Locale.getDefault())
                .format(System.currentTimeMillis())+".jpg")
        val outputOption = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        imageCaptured.takePicture(outputOption,ContextCompat.getMainExecutor(this),
            object: ImageCapture.OnImageSavedCallback{
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {

                    val savedUri = Uri.fromFile(photoFile)
                    getLabels(photoFile)
                    val message = "Foto Guardada en "
                    Toast.makeText(baseContext,"$message $savedUri", Toast.LENGTH_SHORT).show()
                }

                override fun onError(exception: ImageCaptureException) {
                    Log.e(TAG,"Error: ${exception.message}",exception)
                }

            })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQUEST_CAMERA){
            if(allPermissionGranted()){
                Toast.makeText(this,"Se aceptaron Permisos Camara", Toast.LENGTH_SHORT).show()
            }else
            {
                Toast.makeText(this,"No se aceptaron los permisos de Camara", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().build().also {
                    mPreview -> mPreview.setSurfaceProvider(binding.CameraView.surfaceProvider) }
            imageCapture = ImageCapture.Builder().build()
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this,cameraSelector,preview,imageCapture)
                Toast.makeText(this,"Camara", Toast.LENGTH_SHORT).show()
            }catch (e:Exception){
                Log.d(TAG,"Inicio de C??mara fallido: ",e)
            }
        },ContextCompat.getMainExecutor(this))
    }

    private fun allPermissionGranted() = ConstantsCamera.REQUIRED_PERMISSION_CAMERA.all{
        ContextCompat.checkSelfPermission(baseContext,it) == PackageManager.PERMISSION_GRANTED
    }

    fun toActivity(labels: List<Label>) {
        var keysLabels = labels.map({ e -> e.name})
        var namesObject: List<String> =  ArrayList<String>()
        var intent = Intent(this@CameraActivity, ActivityRepeat::class.java)
        if (keysLabels.contains("Bottle") || keysLabels.contains("Plastic")) {
            var array = arrayListOf<String>("Bottle","Plastic")
            namesObject = keysLabels.filter { label ->  array.contains(label)}
            intent = Intent(this@CameraActivity, Information_plastic::class.java)
        } else if (keysLabels.contains("Glass") || keysLabels.contains("Wine") || keysLabels.contains("Beverage")) {
            var array = arrayListOf<String>("Glass","Wine","Beverage")
            namesObject = keysLabels.filter { label ->  array.contains(label)}
            intent = Intent(this@CameraActivity, Information_glass::class.java)
        } else if (keysLabels.contains("Wood") || keysLabels.contains("Paper")) {
            var array = arrayListOf<String>("Wood","Paper")
            namesObject = keysLabels.filter { label ->  array.contains(label)}
            intent = Intent(this@CameraActivity, Information_paper::class.java)
        } else if (keysLabels.contains("food") || keysLabels.contains("dish") || keysLabels.contains("meal")) {
            var array = arrayListOf<String>("food","dish","meal")
            namesObject = keysLabels.filter { label ->  array.contains(label)}
            intent = Intent(this@CameraActivity, Information_organic::class.java)
        } else {
            // TODO
        }
        if (!namesObject.isEmpty()){
            saveCoincidenciaInFirebase(namesObject)
        }
        startActivity(intent)

    }

    fun saveCoincidenciaInFirebase(namesObject: List<String>) {
        val coincidencia = hashMapOf(
            "uid" to FirebaseAuth.getInstance().currentUser?.uid,
            "names" to namesObject
        )
        db.collection("coincidencias")
            .add(coincidencia)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    fun getLabels(photoFile: File) {

        val retrofit = ApiRekognition.getInstance()
        val labelService = retrofit.create(LabelService::class.java)
        var requestBody = RequestBody.create(MediaType.get("image/jpg"), photoFile)
        var filePart = MultipartBody.Part.createFormData("file", photoFile.name, requestBody)
        val myCall: Call<List<Label>> = labelService.detectLabels(filePart)
        myCall.enqueue(object : Callback<List<Label>> {
            override fun onFailure(call: Call<List<Label>>, t: Throwable) {
                Log.e("ERROR", t.message.toString())
            }

            override fun onResponse(call: Call<List<Label>>, response: Response<List<Label>>) {
                Log.e("DATA", "RESPONSE")
                var labels: List<Label> = response.body()!!
                toActivity(labels);
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

}