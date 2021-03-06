package upc.pe.recycleappupc.dataproduct

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import upc.pe.recycleappupc.LabelsActivity
import upc.pe.recycleappupc.PlasticActivity
import upc.pe.recycleappupc.R
import upc.pe.recycleappupc.R.layout
import upc.pe.recycleappupc.databinding.FragmentRecyclableProductsBinding


class RecyclableProductsFragment : Fragment() {

    private lateinit var binding: FragmentRecyclableProductsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecyclableProductsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageView.setOnClickListener {
            it.findNavController().navigate(R.id.actionPlastic)
        }
        binding.imageView2.setOnClickListener {
            it.findNavController().navigate(R.id.actionOrganic)
        }
        binding.imageView14.setOnClickListener {
            it.findNavController().navigate(R.id.actionPaper)
        }
        binding.imageView4.setOnClickListener {
            it.findNavController().navigate(R.id.actionMetal)
        }
        binding.imageView5.setOnClickListener {
            it.findNavController().navigate(R.id.actionGlass)
        }
        binding.imageView6.setOnClickListener {
            it.findNavController().navigate(R.id.actionTetrapack)
        }

    }

    fun toPlasticActivity() {
        val intent = Intent(this.context, PlasticActivity::class.java)
        startActivity(intent)
    }
}