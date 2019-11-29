package sepehr.project.com.androidoreoserviceexample

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import sepehr.project.com.androidoreoserviceexample.di.app.DaggerAppComponent
import timber.log.Timber

class BaseApplication : DaggerApplication() {


    override fun onCreate() {
        super.onCreate()

        initTimber()
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