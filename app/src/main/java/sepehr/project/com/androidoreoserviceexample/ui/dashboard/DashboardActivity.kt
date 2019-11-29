package sepehr.project.com.androidoreoserviceexample.ui.dashboard

import android.os.Bundle
import dagger.android.DaggerActivity
import sepehr.project.com.androidoreoserviceexample.R

class DashboardActivity : DaggerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
    }
}
