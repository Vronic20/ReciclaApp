package upc.pe.recycleappupc

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import upc.pe.recycleappupc.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        verificarPermisoUbicacion()

        binding.imagenMenuHome.setOnClickListener {
            it.findNavController().navigate(R.id.actionProfile)
        }
    }

    private fun verificarPermisoUbicacion() {
        val permisoUbicacion = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);

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
        TODO("Not yet implemented")
    }

}