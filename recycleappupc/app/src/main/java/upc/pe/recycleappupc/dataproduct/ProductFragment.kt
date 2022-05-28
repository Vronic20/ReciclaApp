package upc.pe.recycleappupc.dataproduct

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import upc.pe.recycleappupc.*
import upc.pe.recycleappupc.databinding.FragmentProductBinding


class ProductFragment : Fragment() {


    private lateinit var binding: FragmentProductBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imagenMenuHome.setOnClickListener {
             it.findNavController().navigate(R.id.actionProfile)
         }

        binding.editBuscar.setOnClickListener{
            var text = binding.editBuscar.text.toString().toLowerCase()
            binding.editBuscar.text.clear()
            if (listOf("papel","carton").contains(text)) {
                startActivity(Intent(view.context, Information_paper::class.java))
            } else if (text == "comida") {
                startActivity(Intent(view.context, Information_organic::class.java))
            } else if (text == "vidrio") {
                startActivity(Intent(view.context, Information_glass::class.java))
            } else if (listOf("plastico","botella").contains(text)) {
                startActivity(Intent(view.context, Information_plastic::class.java))
            } else if (text == "tetrapack") {
                startActivity(Intent(view.context, Information_tetrapck::class.java))
            } else if (text == "metal") {
                startActivity(Intent(view.context, Information_metal::class.java))
            } else {
                it.findNavController().navigate(R.id.actionRecyclableProd)
            }
        }

        binding.btnEscanear.setOnClickListener {
            it.findNavController().navigate(R.id.actionCamera)
        }
    }

}