package sepehr.project.com.androidoreoserviceexample

import android.app.Application
import timber.log.Timber

class BaseApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}