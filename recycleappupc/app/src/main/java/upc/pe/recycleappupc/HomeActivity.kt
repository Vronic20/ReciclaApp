package upc.pe.recycleappupc

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavAction
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.google.android.material.bottomnavigation.BottomNavigationView
import upc.pe.recycleappupc.camera.ConstantsCamera
import upc.pe.recycleappupc.databinding.ActivityCameraBinding
import upc.pe.recycleappupc.databinding.ActivityHomeBinding



class HomeActivity : AppCompatActivity() {

    //private lateinit var binding: ActivityHomeBinding
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
                    Toast.makeText(this,"Funciona Puntos Verdes",Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.ItemStatistics -> {
                    Toast.makeText(this,"Funciona Estadisticas",Toast.LENGTH_SHORT).show()
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
            }else
            {
                Toast.makeText(this,"Se necesita los permisos de Ubicacion", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun allPermissionGranted() = ConstantsLocation.REQUIRED_PERMISSION_LOCATION.all{
        ContextCompat.checkSelfPermission(baseContext,it) == PackageManager.PERMISSION_GRANTED
    }
}
