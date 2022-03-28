package upc.pe.recycleappupc.user

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import upc.pe.recycleappupc.R
import upc.pe.recycleappupc.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {


    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentRegisterBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater)
        auth = Firebase.auth
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val registroNombre = binding.editName.text
        val registroCorreo = binding.editEmail.text
        val registroContrasena = binding.editContraseA.text
        binding.btnRegistro.setOnClickListener{
            if(registroNombre.isEmpty()||registroContrasena.isEmpty()||registroCorreo.isEmpty())
            {
                Log.e("Tag","Registre los datos correctamente")
            }
            else{
                registroUsuario(registroNombre.toString(),registroCorreo.toString(),
                    registroContrasena.toString(),it)
            }
        }
    }

    private fun registroUsuario(nombre: String, correo:String, contrasena: String, view: View) {
        if (correo.isNotEmpty() || contrasena.isNotEmpty()) {
            auth.setLanguageCode("es")
            auth.createUserWithEmailAndPassword(correo, contrasena).addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d("Tag", "Se ha logrado registrar al usuario")
                    view.findNavController().navigate(R.id.actionRegisterSuccess)
                } else
                {
                    Log.e("Tag","Hubo un problema durante el registro",it.exception)
                }

            }
        }


    }


}