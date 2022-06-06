package upc.pe.recycleappupc.alarm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import upc.pe.recycleappupc.MainActivity
import upc.pe.recycleappupc.R
import upc.pe.recycleappupc.databinding.FragmentTimePickerBinding


class TimePickerFragment : Fragment() {

    private var idAlarm: Int? = null
    private var isActive: Boolean = true
    private lateinit var binding:FragmentTimePickerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTimePickerBinding.inflate(inflater)
        binding.timePicker1.setIs24HourView(true)
        if (arguments != null) {
            idAlarm = requireArguments().getInt("id")
            if (idAlarm != null) {
                val hour = requireArguments().getInt("hour")
                val minuto = requireArguments().getInt("minuto")
                val etiqueta = requireArguments().getString("etiqueta")
                isActive = requireArguments().getBoolean("active")

                binding.timePicker1.hour = hour!!
                binding.timePicker1.minute = minuto!!
                binding.editEtiqueta.setText(etiqueta)
            }
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnsave.setOnClickListener{
            val result = binding.timePicker1.hour.toString().padStart(2, '0') + ":" + binding.timePicker1.minute.toString().padStart(2, '0')
            val label = binding.editEtiqueta.text.toString()
            val hora = binding.timePicker1.hour
            val minuto = binding.timePicker1.minute
            val isRepetitive = binding.seekBar.progress == 1
            // Use the Kotlin extension in the fragment-ktx artifact
            setFragmentResult("requestKey",
                bundleOf( "id" to idAlarm, "bundleKey" to result, "labelKey" to label, "horaKey" to hora, "minutoKey" to minuto, "repetitiveKey" to isRepetitive, "activeKey" to isActive))
            it.findNavController().navigate(R.id.actionPicktoAlarm)
        }


    }

}