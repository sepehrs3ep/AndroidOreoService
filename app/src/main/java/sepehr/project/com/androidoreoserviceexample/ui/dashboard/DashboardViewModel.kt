package sepehr.project.com.androidoreoserviceexample.ui.dashboard

import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent
import sepehr.project.com.androidoreoserviceexample.component.service_manager.ServiceManager
import javax.inject.Inject

class DashboardViewModel @Inject constructor(private val serviceManager: ServiceManager) :
    ViewModel() {

    val dismissDialogLiveEvent = LiveEvent<Boolean>()
    val finishActivityLiveEvent = LiveEvent<Boolean>()


    fun onExitDialogAcceptClick() {
        serviceManager.stopService()
        dismissDialog()
        finishActivityLiveEvent.value = true
    }

    fun onExitDialogDenyClick() {
        dismissDialog()
    }

    private fun dismissDialog() {
        dismissDialogLiveEvent.value = true
    }

}