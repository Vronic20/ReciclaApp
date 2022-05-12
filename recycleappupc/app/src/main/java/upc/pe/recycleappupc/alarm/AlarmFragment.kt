package upc.pe.recycleappupc.alarm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import upc.pe.recycleappupc.R
import upc.pe.recycleappupc.databinding.FragmentAlarmBinding


class AlarmFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding:FragmentAlarmBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlarmBinding.inflate(inflater)
        auth = Firebase.auth
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val saveData = SaveData(requireContext())
        binding.horatxt.text = saveData.getHour().toString() + ":"+ saveData.getMinute().toString()

        setFragmentResultListener("requestKey") { requestKey, bundle ->
            // We use a String here, but any type that can be put in a Bundle is supported
            val result = bundle.getString("bundleKey")
            val label = bundle.getString("labelKey")
            val Hora = bundle.getInt("horaKey")
            val Minuto = bundle.getInt("minutoKey")
            binding.horatxt.text = result
            binding.etiqueta.text = label

            val saveData = SaveData(requireContext())
            saveData.SaveData(Hora,Minuto)
            saveData.setAlarm()
        }

        binding.btnadd.setOnClickListener {
            it.findNavController().navigate(R.id.actionTimePicker)
        }
        binding.btnback.setOnClickListener{

            it.findNavController().navigate(R.id.actionProfileA)
        }

    }

}