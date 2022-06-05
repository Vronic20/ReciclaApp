package upc.pe.recycleappupc.alarm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.prototype_alarm.view.*
import upc.pe.recycleappupc.R
import upc.pe.recycleappupc.models.AlarmRoom

class AlarmAdapter(var alarms: List<AlarmRoom>,var onAlarmClickListener: OnAlarmClickListener): RecyclerView.Adapter<AlarmPrototype>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmPrototype {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.prototype_alarm, parent, false)
        return AlarmPrototype(view)
    }

    override fun getItemCount(): Int {
        return alarms.size
    }

    override fun onBindViewHolder(holder: AlarmPrototype, position: Int) {
        holder.bind(alarms.get(position), onAlarmClickListener)
    }
}

class AlarmPrototype(itemView: View): RecyclerView.ViewHolder(itemView) {
    val horaTxt = itemView.horatxt
    val etiqueta = itemView.etiqueta
    val switch = itemView.switch1
    val cardView = itemView.cardView

    fun bind(alarm: AlarmRoom, onAlarmClickListener: OnAlarmClickListener) {
        horaTxt.text = alarm.horaString
        etiqueta.text = alarm.etiqueta
        switch.isChecked = alarm.isActive

        cardView.setOnClickListener{
            onAlarmClickListener.onItemClick(alarm)
        }

        switch.setOnCheckedChangeListener({ _ , isChecked ->
            onAlarmClickListener.onSwitchClick(isChecked, alarm)
        })
    }
}
