package upc.pe.recycleappupc.alarm

import upc.pe.recycleappupc.R
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import upc.pe.recycleappupc.databinding.FragmentTimePickerBinding


class TimePickerFragment : Fragment() {


    private lateinit var binding:FragmentTimePickerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTimePickerBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnsave.setOnClickListener{
            val result = binding.timePicker1.hour.toString() + ":" + binding.timePicker1.minute.toString()
            val label = binding.editEtiqueta.text.toString()
            val hora = binding.timePicker1.hour
            val minuto = binding.timePicker1.minute
            // Use the Kotlin extension in the fragment-ktx artifact
            setFragmentResult("requestKey", bundleOf("bundleKey" to result, "labelKey" to label, "horaKey" to hora, "minutoKey" to minuto))
            it.findNavController().navigate(R.id.actionPicktoAlarm)
        }


    }

}