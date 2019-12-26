package sepehr.project.com.androidoreoserviceexample.component.service_manager

import androidx.annotation.IntDef
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class ServiceStateAware @Inject constructor() {
    private val _dashboardServiceStateLiveData = MutableLiveData<Int>(STOPPED)
    val dashboardServiceStateLiveData: LiveData<Int>
        get() = _dashboardServiceStateLiveData

    companion object {
        const val STARTING: Int = 0
        const val STARTED: Int = 1
        const val STOPPING: Int = 2
        const val STOPPED: Int = 3

        @IntDef(
            STARTING,
            STARTED,
            STOPPING,
            STOPPED
        )
        @Retention(AnnotationRetention.SOURCE)
        annotation class DashboardServiceState
    }

    fun setState(@DashboardServiceState state: Int) {
        _dashboardServiceStateLiveData.value = state
    }

    fun isAnyOfSelectedState(vararg state: Int): Boolean {

        val currentState: Int? = _dashboardServiceStateLiveData.value

        state.forEach {

            if (
                currentState != null &&
                currentState == it
            ) {
                return true
            }

        }

        return false
    }
}