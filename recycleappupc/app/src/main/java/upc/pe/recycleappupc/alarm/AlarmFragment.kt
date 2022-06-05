package upc.pe.recycleappupc.alarm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_alarm.*
import kotlinx.android.synthetic.main.prototype_alarm.*
import upc.pe.recycleappupc.R
import upc.pe.recycleappupc.databinding.FragmentAlarmBinding
import upc.pe.recycleappupc.db.AppDataBase
import upc.pe.recycleappupc.models.AlarmRoom


class AlarmFragment : Fragment(), OnAlarmClickListener {
    lateinit var alarms: List<AlarmRoom>
    lateinit var alarmAdapter: AlarmAdapter

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

    fun loadRecyclerView(alarms: List<AlarmRoom>) {
        alarmAdapter = AlarmAdapter(alarms, this)
        rvAlarm.adapter = alarmAdapter
        rvAlarm.layoutManager = LinearLayoutManager(this.context)
    }

    fun loadAlarms() {
        this.context?.let {
            alarms = AppDataBase.getInstance(it).getDAO().getAll()
            loadRecyclerView(alarms)
        }
    }

    override fun onItemClick(alarmaRoom: AlarmRoom) {
        val bundle = bundleOf(
            "id" to alarmaRoom.id,
            "hour" to  alarmaRoom.hora,
            "minuto" to alarmaRoom.minuto,
            "etiqueta" to alarmaRoom.etiqueta,
            "active" to alarmaRoom.isActive
        )
        binding.root.findNavController().navigate(R.id.actionTimePicker, args = bundle)
    }

    override fun onSwitchClick(isChecked: Boolean, alarmaRoom: AlarmRoom) {
        val saveData = SaveData(requireContext())
        if (isChecked) {
            saveData.setAlarm(alarmaRoom)
        } else {
            saveData.onCancel(alarmaRoom)
        }
        alarmaRoom.isActive = isChecked
        AppDataBase.getInstance(requireContext()).getDAO().update(alarm = alarmaRoom)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadAlarms()

        val saveData = SaveData(requireContext())
        setFragmentResultListener("requestKey") { requestKey, bundle ->
            // We use a String here, but any type that can be put in a Bundle is supported
            var id: Int? = bundle.getInt("id")
            if (id == 0) id = null
            val result = bundle.getString("bundleKey")
            val label = bundle.getString("labelKey")
            val Hora = bundle.getInt("horaKey")
            val Minuto = bundle.getInt("minutoKey")
            var isRepetitive = bundle.getBoolean("repetitiveKey")
            var isActive = bundle.getBoolean("activeKey")

            this.context?.let {

                var saveAlarm = AlarmRoom(id, horaString = result!!, hora = Hora, minuto = Minuto, isActive = isActive, etiqueta = label!!, isRepetitive = isRepetitive)
                if (id == null) {
                    val id = AppDataBase.getInstance(it).getDAO().insert(alarm = saveAlarm)
                    saveAlarm = AppDataBase.getInstance(it).getDAO().getById(id.toInt())
                } else {
                    AppDataBase.getInstance(it).getDAO().update(alarm = saveAlarm)
                }
                saveData.setAlarm(saveAlarm)
                loadAlarms()
            }

        }

        binding.btnadd.setOnClickListener {
            it.findNavController().navigate(R.id.actionTimePicker)
        }
        binding.btnback.setOnClickListener{

            it.findNavController().navigate(R.id.actionProfileA)
        }

    }

}

interface OnAlarmClickListener{
    fun onItemClick(alarmaRoom: AlarmRoom)
    fun onSwitchClick(isChecked: Boolean, alarmaRoom: AlarmRoom)
}