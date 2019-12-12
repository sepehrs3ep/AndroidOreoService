package sepehr.project.com.androidoreoserviceexample.di.utils

import dagger.Module
import dagger.android.ContributesAndroidInjector
import sepehr.project.com.androidoreoserviceexample.di.scopes.PerService
import sepehr.project.com.androidoreoserviceexample.service.DashboardService

@Module
abstract class ServiceBuildersModule {
    @PerService
    @ContributesAndroidInjector
    abstract fun contributeDashboardService(): DashboardService
}