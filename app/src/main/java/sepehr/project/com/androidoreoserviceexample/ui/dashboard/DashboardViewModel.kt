package sepehr.project.com.androidoreoserviceexample.ui.dashboard

import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent
import javax.inject.Inject

class DashboardViewModel @Inject constructor() : ViewModel() {

    val dismissDialogLiveEvent = LiveEvent<Boolean>()
    val finishActivityLiveEvent = LiveEvent<Boolean>()


    fun onExitDialogAcceptClick() {
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