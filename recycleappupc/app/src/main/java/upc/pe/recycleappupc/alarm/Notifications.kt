package com.hussein.startup

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.camera.core.impl.utils.ContextUtil.getApplicationContext
import androidx.core.app.NotificationCompat
import upc.pe.recycleappupc.R
import upc.pe.recycleappupc.alarm.AlarmFragment


var NOTIFICATION_CHANNEL_ID = "my_channel_id_01"

class  Notifications(){

    val NOTIFIYTAG="new request"
    fun Notify(context: Context,message:String,number:Int){
        val intent=Intent(context, AlarmFragment::class.java)

        fun createNotificationChannel() {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channelId = "all_notifications" // You should create a String resource for this instead of storing in a variable
                val mChannel = NotificationChannel(
                    channelId,
                    "General Notifications",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                mChannel.description = "This is default channel used for all other notifications"

                val notificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(mChannel)
            }
        }

        createNotificationChannel()
        val channelId = "all_notifications" // Use same Channel ID
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        val builder = NotificationCompat.Builder(context, channelId) // Create notification with channel Id
            .setSmallIcon(R.drawable.addalarm)
            .setContentTitle("El camión de basura está en camino")
            .setContentText("Preparate para sacar la basura.")
            .setPriority(NotificationCompat.PRIORITY_MAX)
        builder.setContentIntent(pendingIntent).setAutoCancel(true)
        val mNotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        with(mNotificationManager) {
            notify(123, builder.build())
        }


    }

}