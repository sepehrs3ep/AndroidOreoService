package sepehr.project.com.androidoreoserviceexample.di.utils

import dagger.Module
import dagger.android.ContributesAndroidInjector
import sepehr.project.com.androidoreoserviceexample.broadcast.DashboardServiceActionManagerBroadcastReceiver
import sepehr.project.com.androidoreoserviceexample.di.scopes.PerBroadcastReceiver

@Module
abstract class BroadcastReceiverBuildersModule {

    @PerBroadcastReceiver
    @ContributesAndroidInjector
    abstract fun contributeDashboardServiceActionManagerBroadcastReceiver(): DashboardServiceActionManagerBroadcastReceiver

}