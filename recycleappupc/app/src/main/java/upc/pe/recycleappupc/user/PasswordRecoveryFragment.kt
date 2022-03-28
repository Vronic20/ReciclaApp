package upc.pe.recycleappupc.user

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import upc.pe.recycleappupc.R
import upc.pe.recycleappupc.databinding.FragmentPasswordRecoveryBinding


class PasswordRecoveryFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentPasswordRecoveryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPasswordRecoveryBinding.inflate(inflater)
        auth = Firebase.auth
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val correo = binding.editRecEmail.text
        binding.btnRecuperar.setOnClickListener{
            if(correo.isEmpty()) {
                Log.e("Tag","Ingrese el correo correctamente")
            } else {
                enviarCorreo(correo.toString(),it)
            }
        }

    }

    private fun enviarCorreo(correo:String, view: View){
        if(correo.isNotEmpty()){
        auth.setLanguageCode("es")
        auth.sendPasswordResetEmail(correo).addOnCompleteListener{
                if(it.isSuccessful)
                {
                 Log.d("Tag","Se ha logrado enviar el correo")
                 view.findNavController().navigate(R.id.actionPasswRecovSuccess)
                } else
                 {
                    Log.e("Tag","No se ha podido enviar el correo",it.exception)
                 }
            }
        }
    }

}