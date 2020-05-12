package udit.programmer.co.firebase

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_notification.*

class NotificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            nm.createNotificationChannel(
                NotificationChannel(
                    "first",
                    "default",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            )
            val channel =
                NotificationChannel("second", "default", NotificationManager.IMPORTANCE_HIGH)
            channel.apply {
                enableLights(true)
                enableVibration(true)
            }
            nm.createNotificationChannel(channel)


        }
        btn1.setOnClickListener {
            val simple_Notification = NotificationCompat.Builder(this, "first")
                .setContentTitle("Simple Notification")
                .setContentText("This is a simple Notification")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()
            nm.notify(1, simple_Notification)
        }

        btn2.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
            val pending_intent =
                PendingIntent.getActivity(this, 123, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            val clickable_notification = NotificationCompat.Builder(this, "first")
                .setContentTitle("Clickble Notification")
                .setAutoCancel(true)
                .setContentIntent(pending_intent)
                .setContentText("This is a Clickable Notification")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()
            nm.notify(2, clickable_notification)
        }

        btn3.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
            val pending_intent =
                PendingIntent.getActivity(this, 123, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            val btn_clickable_notification = NotificationCompat.Builder(this, "first")
                .setContentTitle("Clickble Notification")
                .setAutoCancel(true)
                .addAction(R.drawable.ic_launcher_foreground, "Click Me", pending_intent)
                .setContentText("This is a Clickable Notification")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()
            nm.notify(3, btn_clickable_notification)
        }

        btn4.setOnClickListener {
            val builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationCompat.Builder(this, "second")
            } else {
                NotificationCompat.Builder(this)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setDefaults(Notification.DEFAULT_VIBRATE or Notification.DEFAULT_LIGHTS)
            }
            val headsup_Notification = builder
                .setContentTitle("HeadsUP Notification")
                .setContentText("This is a HeadsUP Notification")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .build()
            nm.notify(4, headsup_Notification)
        }

    }

}
