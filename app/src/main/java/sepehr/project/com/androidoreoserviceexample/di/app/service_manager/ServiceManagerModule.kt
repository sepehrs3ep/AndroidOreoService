package sepehr.project.com.androidoreoserviceexample.di.app.service_manager

import android.content.Context
import dagger.Module
import dagger.Provides
import sepehr.project.com.androidoreoserviceexample.component.service_manager.ServiceManager
import sepehr.project.com.androidoreoserviceexample.component.service_manager.ServiceStateAware
import sepehr.project.com.androidoreoserviceexample.di.scopes.AppScope

@Module
object ServiceManagerModule {

    @JvmStatic
    @AppScope
    @Provides
    fun provideServiceManager(
        context: Context,
        serviceStateAware: ServiceStateAware
    ): ServiceManager = ServiceManager(context, serviceStateAware)
}