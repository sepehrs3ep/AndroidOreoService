package sepehr.project.com.androidoreoserviceexample.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import dagger.android.DaggerService
import sepehr.project.com.androidoreoserviceexample.R
import sepehr.project.com.androidoreoserviceexample.broadcast.DashboardServiceActionManagerBroadcastReceiver

class DashboardService : DaggerService() {

    @Volatile
    private var notification: Notification? = null

    companion object {
        private const val CHANNEL_ID: String =
            "sepehr.project.com.androidoreoserviceexample.serviceChannel"
        private const val MAIN_SERVICE_START_COMMAND_KEY: String =
            "sepehr.project.com.androidoreoserviceexample.service.MAIN_SERVICE_START_COMMAND_KEY"
        private const val ONGOING_NOTIFICATION_ID: Int = 121412
        private const val MAIN_SERVICE_START_COMMAND: Int = 1

        fun getStartIntent(context: Context): Intent =
            getIntent(context).apply {
                this.putExtra(
                    MAIN_SERVICE_START_COMMAND_KEY,
                    MAIN_SERVICE_START_COMMAND
                )
            }

        fun getIntent(context: Context): Intent = Intent(context, DashboardService::class.java)
    }

    override fun onCreate() {
        super.onCreate()
        start()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent == null) {
            return Service.START_NOT_STICKY
        }

        val commandValue = intent.getIntExtra(MAIN_SERVICE_START_COMMAND_KEY, -1)

        if (commandValue == MAIN_SERVICE_START_COMMAND) {
            start()
            return Service.START_STICKY
        }

        return Service.START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    @Synchronized
    private fun start() {
        createNotificationAndStartForeground()
    }

    @Synchronized
    private fun createNotificationAndStartForeground() {

        if (notification == null) {
            notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(
                    getText(
                        R.string.app_name
                    )
                )
                .setContentText(
                    getText(
                        R.string.service_body_text
                    )
                )
                .setSmallIcon(
                    R.drawable.ic_launcher_foreground
                )
                .setTicker(
                    getText(
                        R.string.app_name
                    )
                )
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setContentIntent(
                    getOpenActionIntent()
                )
                .addAction(
                    NotificationCompat.Action(
                        R.drawable.ic_launcher_foreground,
                        getString(R.string.service_exit_action_text),
                        getStopActionIntent()
                    )
                )
                .build()
                .also {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        createNotificationChannel()
                    }
                }
        }

        startForeground(
            ONGOING_NOTIFICATION_ID,
            notification
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Synchronized
    private fun createNotificationChannel() {
        val notificationManager: NotificationManager? =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (
            notificationManager != null &&
            notificationManager.getNotificationChannel(CHANNEL_ID) == null
        ) {
            val notificationChannel: NotificationChannel = NotificationChannel(
                CHANNEL_ID,
                getString(R.string.app_name),
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                this.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            }

            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun getStopActionIntent(): PendingIntent {
        return DashboardServiceActionManagerBroadcastReceiver
            .getBroadCastPendingIntent(
                context = applicationContext,
                action = DashboardServiceActionManagerBroadcastReceiver.ACTION_STOP_SERVICE
            )
    }

    private fun getOpenActionIntent(): PendingIntent {
        return DashboardServiceActionManagerBroadcastReceiver
            .getBroadCastPendingIntent(
                context = applicationContext,
                action = DashboardServiceActionManagerBroadcastReceiver.ACTION_OPEN_ACTIVITY
            )
    }


}
