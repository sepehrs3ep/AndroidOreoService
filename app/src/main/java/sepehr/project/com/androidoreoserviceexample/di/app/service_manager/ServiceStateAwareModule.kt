package sepehr.project.com.androidoreoserviceexample.di.app.service_manager

import dagger.Module
import dagger.Provides
import sepehr.project.com.androidoreoserviceexample.component.service_manager.ServiceStateAware
import sepehr.project.com.androidoreoserviceexample.di.scopes.AppScope

@Module
object ServiceStateAwareModule {

    @JvmStatic
    @AppScope
    @Provides
    fun provideServiceStateAware(): ServiceStateAware = ServiceStateAware()
}