package upc.pe.recycleappupc.user

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import upc.pe.recycleappupc.EstadisticasActivity
import upc.pe.recycleappupc.R
import upc.pe.recycleappupc.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {


    private lateinit var auth: FirebaseAuth
    private lateinit var binding:FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater)
        auth = Firebase.auth
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCerrarSesion.setOnClickListener {
            auth.signOut()
            it.findNavController().navigate(R.id.actionLogin)
            Toast.makeText(context,"Sesión Cerrada",Toast.LENGTH_LONG).show()
        }
        binding.btnAlarma.setOnClickListener{

            it.findNavController().navigate(R.id.actionAlarm)
        }
    }

}