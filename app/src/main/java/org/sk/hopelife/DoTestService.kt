package org.sk.hopelife

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.content.Context.ACTIVITY_SERVICE
import android.content.Intent
import android.content.res.Resources
import android.os.Build
import android.preference.PreferenceManager
import android.preference.PreferenceManager.getDefaultSharedPreferences
import android.util.Log
import androidx.core.app.NotificationCompat
import java.util.*


class DoTestService : JobService() {

    lateinit var manager: NotificationManager

    override fun onStartJob(params: JobParameters?): Boolean {
        val preferences = application.applicationContext.getSharedPreferences("quiz", 0)
        val mode = preferences.getBoolean("timer", false)
        manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        Log.d("fuck", mode.toString())
        Log.d("fuck", "Started")

        val calendar = Calendar.getInstance()
        val t = Timer()
        if (mode){
            t.schedule(object : TimerTask(){
                override fun run() {
                    sendNotif("Пора пройти тест", "Пройдите тест на COVID-19")
                }
            }, 7200000L, 7200000L)
        }else{
            t.schedule(object : TimerTask(){
                override fun run() {
                    if (calendar.get(Calendar.HOUR_OF_DAY) == 11 || calendar.get(Calendar.HOUR_OF_DAY) == 21){
                        sendNotif("Пора пройти тест", "Пройдите тест на COVID-19")
                    }
                }
            }, 0, 3600000L)

        }
        return true

    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d("fuck", "Stoped")
        return true
    }

    private fun sendNotif(notifTitle: String, notifMess: String) {
        val preferences = getDefaultSharedPreferences(this)
        val notifyID = preferences.getInt("IdNotif", 0)
        val editor = preferences.edit()
        if (notifyID < 30) {
            editor.putInt("IdNotif", notifyID + 1)
            editor.apply()
        } else {
            editor.putInt("IdNotif", 0)
            editor.apply()
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            val notificationIntent = Intent(this, COVIDTestAcrivity::class.java)
            val contentIntent = PendingIntent.getActivity(
                this,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT
            )
            val builder = NotificationCompat.Builder(this)
            builder.setContentIntent(contentIntent) // обязательные настройки
                .setSmallIcon(R.drawable.ic_fighting)
                .setContentTitle(notifTitle) // Заголовок уведомления
                .setContentText(notifMess) // Текст уведомления
                .setAutoCancel(true) // автоматически закрыть уведомление после нажатия
            manager.notify(1, builder.build())

        } else {
            val intent = Intent(this, COVIDTestAcrivity::class.java)
            val pIntent = PendingIntent.getActivity(this, 0, intent, 0)
            val CHANNEL_ID = "my_channel_01" // The id of the channel.
            val channel = NotificationChannel(
                "my_channel_01",
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notification = Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_fighting)
                .setContentTitle(notifTitle)
                .setContentText(notifMess)
                .setContentIntent(pIntent)
                .setChannelId(CHANNEL_ID).build()
            notification.flags = notification.flags or Notification.FLAG_AUTO_CANCEL
            if (notifyID == 0) {
                manager.cancelAll()
            }
            manager.createNotificationChannel(channel)
            manager.notify(notifyID, notification)

            this.stopSelf()
        }
    }
}
