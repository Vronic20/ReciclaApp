package upc.pe.recycleappupc

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import upc.pe.recycleappupc.databinding.ActivityHomeBinding

private const val REQUEST_LOCATION = 50

class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),50)
        //verificarPermisoUbicacion()
    }


    private fun verificarPermisoUbicacion() {
        val permisoUbicacion =
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) ;

        if(permisoUbicacion == PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(this,"Permiso de Ubicacion otorgado",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,"Permiso de Ubicacion denegado",Toast.LENGTH_LONG).show()
            //Manifest.permission.ACCESS_COARSE_LOCATION
            permisoRechazado()
        }
    }

    private fun permisoRechazado() {
        val permRechazo = ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_COARSE_LOCATION)
        if(permRechazo){
            null
        }
    }
}