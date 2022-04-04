package upc.pe.recycleappupc.dataproduct

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import upc.pe.recycleappupc.R
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
            it.findNavController().navigate(R.id.actionSearchedProduct)
        }
    }

}