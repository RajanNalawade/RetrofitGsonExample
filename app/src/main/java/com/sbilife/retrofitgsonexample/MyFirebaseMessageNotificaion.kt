package com.sbilife.retrofitgsonexample

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Intent
import android.os.Build
import android.util.Log.d
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessageNotificaion : FirebaseMessagingService() {

    companion object {
        const val channel_id = BuildConfig.APPLICATION_ID
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        d("Firebase", "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        /*if (remoteMessage.data.size > 0) {
            d("Firebase", "Message data payload: " + remoteMessage.getData());

            if ( Check if data needs to be processed by long running job  true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                scheduleJob();
            } else {
                // Handle message within 10 seconds
                handleNow();
            }

        }*/

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            d(
                "Firebase",
                "Message Notification Body: " + remoteMessage.getNotification()!!.getBody()
            );

            remoteMessage.notification!!.body?.let { showNotifications(it) }
        }
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

    fun showNotifications(msg: String) {

        val mIntent = Intent(this, MainActivity::class.java)
        mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        // pendingIntent is an intent for future use i.e after
        // the notification is clicked, this intent will come into action
        // FLAG_UPDATE_CURRENT specifies that if a previous
        // PendingIntent already exists, then the current one
        // will update it with the latest intent
        // 0 is the request code, using it later with the
        // same method again will get back the same pending
        // intent for future reference
        // intent passed here is to our afterNotification class
        val pi = PendingIntent.getActivity(this, 0, mIntent, FLAG_UPDATE_CURRENT)

        val mNotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            var notificationChannel = NotificationChannel(channel_id, "My Notifications", NotificationManager.IMPORTANCE_DEFAULT);

            // Configure the notification channel.
            notificationChannel.description = "Channel description"
            notificationChannel.enableLights(true)
            notificationChannel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
            notificationChannel.enableVibration(true)
            mNotificationManager.createNotificationChannel(notificationChannel)
        }

        val mNotification = NotificationCompat.Builder(this, channel_id)
            .setSmallIcon(R.drawable.ic_stat_name)
            .setContentTitle("Firebase Notification")
            .setContentText(msg)
            .setContentIntent(pi)
            .setAutoCancel(true)
            .build()

        mNotificationManager.notify(1, mNotification)
    }
}
