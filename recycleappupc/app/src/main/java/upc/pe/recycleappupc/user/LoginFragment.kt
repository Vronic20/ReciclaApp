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
import upc.pe.recycleappupc.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentLoginBinding
    private lateinit var userEmail: EditText
    private lateinit var userContrasena: EditText


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater)
        auth = Firebase.auth
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRegistrar.setOnClickListener{
            it.findNavController().navigate(R.id.actionRegisterUser)
        }
        binding.btnIngresar.setOnClickListener{
            userEmail = binding.editEmail
            userContrasena = binding.editContraseA
            if(userEmail.text.isEmpty() || userContrasena.text.isEmpty()){
               Log.e("Tag","Ingrese los datos correctos")
            } else {
                inicioSesion(userEmail, userContrasena, it)
            }
        }
        binding.txtOlvidasteContraseA.setOnClickListener{
            it.findNavController().navigate(R.id.actionPasswordRecover)
        }
    }

    private fun inicioSesion(email: EditText, contrasena: EditText, view: View) {
        val mEmail = email.text.toString()
        val mContrasena = contrasena.text.toString()
        if (mEmail.isNotEmpty() && mContrasena.isNotEmpty()) {
            auth.signInWithEmailAndPassword(mEmail, mContrasena)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Log.d("Tag","Se ha logrado iniciar sesion")
                        view.findNavController().navigate(R.id.actionHome)
                    } else {
                        Log.e("Tag","No se ha podido Iniciar Sesión",it.exception)
                    }
                }
        }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            view?.findNavController()?.navigate(R.id.actionHome)
        }
    }


}