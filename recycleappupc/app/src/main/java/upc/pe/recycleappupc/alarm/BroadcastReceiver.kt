package upc.pe.recycleappupc.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.hussein.startup.Notifications

class BroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        if(intent!!.action.equals("com.tester.alarmmanager")){
            var b = intent.extras
                Toast.makeText(context, "¡El camion de basura está en su destino!", Toast.LENGTH_LONG).show()
            val notifyMe = Notifications()
            notifyMe.Notify(context!!, "¡El camion de basura está en su destino!",10)
        }
        else if (intent!!.action.equals("android.intent.action.BOOT_COMPLETED")){
            val saveData = SaveData(context!!)
            saveData.setAlarm()
        }

    }
}