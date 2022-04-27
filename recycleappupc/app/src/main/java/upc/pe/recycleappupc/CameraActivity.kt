package upc.pe.recycleappupc

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import upc.pe.recycleappupc.camera.ConstantsCamera
import upc.pe.recycleappupc.camera.ConstantsCamera.TAG
import upc.pe.recycleappupc.databinding.ActivityCameraBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.logging.SimpleFormatter


class CameraActivity : AppCompatActivity() {

    private val IMAGE_GALLERY_REQUEST_CODE: Int = 2001
    private lateinit var binding: ActivityCameraBinding
    private val REQUEST_CAMERA = 100
    private var imageCapture:ImageCapture? = null
    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor:ExecutorService

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

        binding.btnCamara.setOnClickListener{
            takePhoto()
        }

    val getImage = registerForActivityResult(
        ActivityResultContracts.GetContent(),
        ActivityResultCallback {
            // binding.dondemostrarlaimg.setImageURI(it)
        }
    )
        binding.btnGallery.setOnClickListener {
            getImage.launch("image/*")
        }

    }

    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let { mFile -> File(mFile,resources.getString(R.string.app_name)).apply {
            mkdirs()
        } }

        return if(mediaDir != null && mediaDir.exists())
            mediaDir else filesDir
    }

    private fun takePhoto() {
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
                Log.d(TAG,"Inicio de Cámara fallido: ",e)
            }
        },ContextCompat.getMainExecutor(this))
    }

    private fun allPermissionGranted() = ConstantsCamera.REQUIRED_PERMISSION_CAMERA.all{
        ContextCompat.checkSelfPermission(baseContext,it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

}