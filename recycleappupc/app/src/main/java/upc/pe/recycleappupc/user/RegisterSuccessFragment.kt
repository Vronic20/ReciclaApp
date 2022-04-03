package upc.pe.recycleappupc.user

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import upc.pe.recycleappupc.R
import upc.pe.recycleappupc.databinding.FragmentRegistersuccessBinding


class RegisterSuccessFragment : Fragment() {

    private lateinit var binding: FragmentRegistersuccessBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistersuccessBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnVolverInicio.setOnClickListener(){
            it.findNavController().navigate(R.id.actionReturnLog)
        }

    }
}