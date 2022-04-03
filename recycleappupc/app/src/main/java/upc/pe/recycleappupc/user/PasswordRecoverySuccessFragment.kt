package upc.pe.recycleappupc.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import upc.pe.recycleappupc.R
import upc.pe.recycleappupc.databinding.FragmentPasswordRecoverySuccessBinding


class PasswordRecoverySuccessFragment : Fragment() {

    private lateinit var binding: FragmentPasswordRecoverySuccessBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPasswordRecoverySuccessBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnVolverInicio2.setOnClickListener(){
            it.findNavController().navigate(R.id.actionReturnLogin)
        }

    }
}