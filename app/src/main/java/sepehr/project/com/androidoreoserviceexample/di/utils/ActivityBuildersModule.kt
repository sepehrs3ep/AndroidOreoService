package sepehr.project.com.androidoreoserviceexample.di.utils

import dagger.Module
import dagger.android.ContributesAndroidInjector
import sepehr.project.com.androidoreoserviceexample.di.scopes.PerActivity
import sepehr.project.com.androidoreoserviceexample.di.view_model.DashboardViewModelsModule
import sepehr.project.com.androidoreoserviceexample.ui.dashboard.DashboardActivity

@Module
abstract class ActivityBuildersModule {
    @PerActivity
    @ContributesAndroidInjector(modules = [DashboardViewModelsModule::class])
    abstract fun dashboardActivityInjector(): DashboardActivity
}