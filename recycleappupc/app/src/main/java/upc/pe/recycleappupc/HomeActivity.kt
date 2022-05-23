package upc.pe.recycleappupc

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavAction
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import upc.pe.recycleappupc.camera.ConstantsCamera
import upc.pe.recycleappupc.databinding.ActivityCameraBinding
import upc.pe.recycleappupc.databinding.ActivityHomeBinding



class HomeActivity : AppCompatActivity() {

    //private lateinit var binding: ActivityHomeBinding
    private val db = Firebase.firestore
    private val REQUEST_LOCATION = 50
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),REQUEST_LOCATION)
        if(!allPermissionGranted()) {
            ActivityCompat.requestPermissions(this,
                ConstantsLocation.REQUIRED_PERMISSION_LOCATION,
                ConstantsLocation.REQUEST_LOCATION)
        }

        val homeBottomNav = findViewById<BottomNavigationView>(R.id.home_buttom_navigation)
        homeBottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.ItemReconocer -> {
                    startActivity(Intent(baseContext,CameraActivity::class.java))
                    true
                }
                R.id.ItemGreenPoints -> {
                    startActivity(Intent(baseContext,puntos_verdes::class.java))
                    true
                }
                R.id.ItemStatistics -> {
                    startActivity(Intent(baseContext,EstadisticasActivity::class.java))
                    true
                }
                else -> {false}
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQUEST_LOCATION){
            if(allPermissionGranted()){
                Toast.makeText(this,"Permiso de Ubicación garantizado", Toast.LENGTH_SHORT).show()
                saveUbicacion()
            } else {
                Toast.makeText(this,"Se necesita los permisos de Ubicacion", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    fun saveUbicacion(){
        var ubicacion = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        ubicacion.lastLocation.addOnCompleteListener(this){ task ->
            val location: Location = task.result
            if (location == null) {
                Toast.makeText(this,"No hay ubicación para almacenar", Toast.LENGTH_SHORT).show()
            } else {
                val ubicacion = hashMapOf(
                    "latitude" to location.latitude,
                    "longitude" to location.longitude
                )
                db.collection("ubicaciones")
                    .document(FirebaseAuth.getInstance().currentUser?.uid?.toString()!!)
                    .set(ubicacion)
                    .addOnSuccessListener { documentReference ->
                        Log.d(ConstantsCamera.TAG, "DocumentSnapshot added")
                    }
                    .addOnFailureListener { e ->
                        Log.w(ConstantsCamera.TAG, "Error adding document", e)
                    }
            }
        }
    }
    private fun allPermissionGranted() = ConstantsLocation.REQUIRED_PERMISSION_LOCATION.all{
        ContextCompat.checkSelfPermission(baseContext,it) == PackageManager.PERMISSION_GRANTED
    }
}
