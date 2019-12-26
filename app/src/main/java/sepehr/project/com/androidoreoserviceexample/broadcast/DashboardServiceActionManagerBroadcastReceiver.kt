package sepehr.project.com.androidoreoserviceexample.broadcast

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import dagger.android.DaggerBroadcastReceiver
import sepehr.project.com.androidoreoserviceexample.component.service_manager.ServiceManager
import sepehr.project.com.androidoreoserviceexample.ui.dashboard.DashboardActivity
import timber.log.Timber
import javax.inject.Inject

class DashboardServiceActionManagerBroadcastReceiver : DaggerBroadcastReceiver() {

    @Inject
    lateinit var serviceManager: ServiceManager

    companion object {
        const val ACTION_OPEN_ACTIVITY: String =
            "sepehr.project.com.androidoreoserviceexample.broadcast.open_activity"
        const val ACTION_STOP_SERVICE: String =
            "sepehr.project.com.androidoreoserviceexample.broadcast.stop_service"

        fun getBroadCastPendingIntent(
            context: Context,
            action: String
        ): PendingIntent {
            val intent: Intent = Intent(
                context,
                DashboardServiceActionManagerBroadcastReceiver::class.java
            ).apply {
                this.flags = Intent.FLAG_RECEIVER_FOREGROUND
                this.action = action
            }
            return PendingIntent.getBroadcast(
                context,
                0,
                intent,
                0
            )
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)

        if (intent == null) {
            return
        }

        val action: String? = intent.action

        action?.let {

            if (ACTION_OPEN_ACTIVITY == it) {
                if (context != null) {
                    startActivity(
                        context,
                        DashboardActivity::class.java.name
                    )
                }

                return
            }

            if (ACTION_STOP_SERVICE == it) {
                serviceManager.stopService()
            }
        }
    }

    private fun startActivity(
        context: Context,
        lastActivityLocaleName: String
    ) {
        try {
            val startIntent = Intent().apply {
                this.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_SINGLE_TOP or
                        Intent.FLAG_ACTIVITY_CLEAR_TOP
                this.setClassName(context.packageName, lastActivityLocaleName)
            }
            context.startActivity(startIntent)
        } catch (ex: Exception) {
            Timber.e(ex)
        }
    }

}
