package sepehr.project.com.androidoreoserviceexample.component.service_manager

import android.content.Context
import androidx.core.content.ContextCompat
import sepehr.project.com.androidoreoserviceexample.service.DashboardService
import javax.inject.Inject

class ServiceManager @Inject constructor(
    private val context: Context,
    private val serviceStateAware: ServiceStateAware
) {
    @Synchronized
    fun startService() {
        if (
            serviceStateAware.isAnyOfSelectedState(
                ServiceStateAware.STOPPING,
                ServiceStateAware.STOPPED
            ).not()
        ) {
            return
        }
        serviceStateAware.setState(ServiceStateAware.STARTING)

        ContextCompat.startForegroundService(
            context,
            DashboardService.getStartIntent(context)
        )
    }

    @Synchronized
    fun stopService() {
        if (
            serviceStateAware.isAnyOfSelectedState(
                ServiceStateAware.STARTED
            ).not()
        ) {
            return
        }

        serviceStateAware.setState(
            ServiceStateAware.STOPPING
        )

        context.stopService(
            DashboardService.getIntent(context)
        )
    }
}