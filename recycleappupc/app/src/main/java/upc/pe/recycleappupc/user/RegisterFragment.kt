package upc.pe.recycleappupc.user

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.util.PatternsCompat
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import upc.pe.recycleappupc.R
import upc.pe.recycleappupc.databinding.FragmentRegisterBinding
import java.util.regex.Pattern

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
        val registroNombre = binding.editName
        val registroCorreo = binding.editEmail
        val registroContrasena = binding.editContraseA
        binding.btnRegistro.setOnClickListener{
            if(registroContrasena.text.isEmpty()||registroCorreo.text.isEmpty())
            {
                Log.e("Tag","Registre los datos correctamente")
                Toast.makeText(context,"Registre los datos correctamente",Toast.LENGTH_SHORT).show();
            }
            else{
                registroUsuario(registroNombre,registroCorreo,
                    registroContrasena,it)
            }
        }
    }

    private fun registroUsuario(nombre: EditText, correo:EditText, contrasena: EditText, view: View) {
        val mCorreo = correo.text.toString()
        val mContrasena = contrasena.text.toString()
        val mNombre = nombre.text.toString()
        val passwordRegex = Pattern.compile("^" + ".{8,16}")
        if (mCorreo.isNotEmpty() || mContrasena.isNotEmpty()
            || !PatternsCompat.EMAIL_ADDRESS.matcher(mCorreo).matches()
            || !passwordRegex.matcher(mContrasena).matches()) {
            auth.setLanguageCode("es")
            auth.createUserWithEmailAndPassword(mCorreo, mContrasena).addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d("Tag", "Se ha logrado registrar al usuario")
                    view.findNavController().navigate(R.id.actionRegisterSuccess)
                } else
                {
                    Log.e("Tag","Hubo un problema durante el registro",it.exception)
                    Toast.makeText(context,"Hubo un problema durante el registro",Toast.LENGTH_SHORT).show();
                }

            }
        }
        if (mCorreo.isEmpty()){
            correo.error = "Tiene que insertar un email"
        }
        else if (mContrasena.isEmpty()){
            contrasena.error = "Tiene que insertar la contraseña"
        }
        else if (!PatternsCompat.EMAIL_ADDRESS.matcher(mCorreo).matches()){
            correo.error = "No ha insertado un email válido"
        }
        else if(!passwordRegex.matcher(mContrasena).matches())
        {
            contrasena.error = "Debe ingresar una contraseña entre 8 a 16 digitos"
        }


    }


}