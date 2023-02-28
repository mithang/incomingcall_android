package com.example.incomingcall_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.widget.Button
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnCall).setOnClickListener {
            showIncomingCallNotification(this,"tran van minh","0398267083")
        }

    }

    fun showIncomingCallNotification(context: Context, callerName: String, callerNumber: String) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "incoming_call_channel_id"
            val channelName = "Incoming Call Channel"
            val channelDescription = "Channel for incoming call notifications"
            val channelImportance = NotificationManager.IMPORTANCE_HIGH

            val notificationChannel = NotificationChannel(channelId, channelName, channelImportance).apply {
                description = channelDescription
                enableLights(true)
                lightColor = Color.RED
                enableVibration(true)
            }

            notificationManager.createNotificationChannel(notificationChannel)
        }

        val notificationSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)

        val notificationBuilder = NotificationCompat.Builder(context, "incoming_call_channel_id")
            .setSmallIcon(androidx.loader.R.drawable.notification_bg)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, androidx.core.R.drawable.notification_bg))
            .setContentTitle("Incoming call from $callerName")
            .setContentText(callerNumber)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_CALL)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setSound(notificationSoundUri)
            .setFullScreenIntent(getFullScreenIntent(context, callerName,callerNumber), true)

        notificationManager.notify(1, notificationBuilder.build())
    }

    fun getFullScreenIntent(context: Context,name:String,phone:String): PendingIntent {
        val intent = Intent(context, IncomingCallActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra("caller_name",name)
        intent.putExtra("caller_number",phone)
        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }
}