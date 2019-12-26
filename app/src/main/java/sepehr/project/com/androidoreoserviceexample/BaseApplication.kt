package sepehr.project.com.androidoreoserviceexample

import android.app.Activity
import android.os.Bundle
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import sepehr.project.com.androidoreoserviceexample.component.service_manager.ServiceManager
import sepehr.project.com.androidoreoserviceexample.di.app.DaggerAppComponent
import timber.log.Timber
import javax.inject.Inject

class BaseApplication : DaggerApplication() {
    @Inject
    lateinit var serviceManager: ServiceManager

    private val activityLifecycleCallbacks =
        object : ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity) {
                //ignore
            }

            override fun onActivityStarted(activity: Activity) {
                //ignore
            }

            override fun onActivityDestroyed(activity: Activity) {
                //ignore
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                //ignore
            }

            override fun onActivityStopped(activity: Activity) {
                //ignore
            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                serviceManager.startService()
            }

            override fun onActivityResumed(activity: Activity) {
                //ignore
            }
        }


    override fun onCreate() {
        super.onCreate()

        initTimber()

        registerActivityLifecycleCallbacks(
            activityLifecycleCallbacks
        )
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().baseApplication(this).build()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}