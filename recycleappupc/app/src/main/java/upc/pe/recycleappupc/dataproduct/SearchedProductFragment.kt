package upc.pe.recycleappupc.dataproduct

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import upc.pe.recycleappupc.R
import upc.pe.recycleappupc.databinding.FragmentSearchedProductBinding


class SearchedProductFragment : Fragment() {

    private lateinit var binding: FragmentSearchedProductBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchedProductBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}