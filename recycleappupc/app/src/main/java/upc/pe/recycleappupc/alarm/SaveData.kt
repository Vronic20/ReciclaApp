package upc.pe.recycleappupc.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import upc.pe.recycleappupc.models.AlarmRoom
import java.util.*

class SaveData{

    var context: Context?=null
    var alarmManger: AlarmManager
    var sharedRef:SharedPreferences?=null
    constructor(context:Context){
        this.context = context
        sharedRef=context.getSharedPreferences("myref", Context.MODE_PRIVATE)
        alarmManger = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }

    fun SaveData(hour:Int, minute:Int){
        var editor = sharedRef!!.edit()
        editor.putInt("hour", hour)
        editor.putInt("minute", minute)
        editor.commit()
    }

    fun getHour():Int{
        return sharedRef!!.getInt("hour", 0)
    }

    fun getMinute():Int{
        return sharedRef!!.getInt("minute", 0)
    }

    fun setAlarm(alarm: AlarmRoom){
        //Set Calendar
        val calender = Calendar.getInstance()
        calender.set(Calendar.HOUR_OF_DAY, alarm.hora)
        calender.set(Calendar.MINUTE, alarm.minuto)
        calender.set(Calendar.SECOND, 0)

        val intent = getIntent()
        val pi = PendingIntent.getBroadcast(context, alarm.id!!,intent, PendingIntent.FLAG_UPDATE_CURRENT)
        if (alarm.isActive) {
            if (alarm.isRepetitive) {
                alarmManger.setRepeating(AlarmManager.RTC_WAKEUP, calender.timeInMillis, AlarmManager.INTERVAL_DAY, pi)
            } else {
                alarmManger.set(AlarmManager.RTC_WAKEUP, calender.timeInMillis, pi)
            }
        }
    }

    fun getIntent(): Intent {
        var intent = Intent(context, BroadcastReceiver::class.java)
        intent.putExtra("message", "¡El camion de basura está en su destino!")
        intent.action = "com.tester.alarmmanager"
        return intent
    }

    fun onCancel(alarm: AlarmRoom) {
        val intent = getIntent()
        val pi = PendingIntent.getBroadcast(context, alarm.id!!,intent, PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManger.cancel(pi)
    }
}