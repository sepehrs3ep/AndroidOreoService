package sepehr.project.com.androidoreoserviceexample.di.app

import android.content.Context
import dagger.Module
import dagger.Provides
import sepehr.project.com.androidoreoserviceexample.BaseApplication
import sepehr.project.com.androidoreoserviceexample.di.scopes.AppScope

@Module
object AppModule {

    @JvmStatic
    @AppScope
    @Provides
    fun provideContext(baseApplication: BaseApplication): Context {
        return baseApplication.applicationContext
    }

}