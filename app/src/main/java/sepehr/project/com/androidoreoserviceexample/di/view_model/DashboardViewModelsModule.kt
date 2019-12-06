/*
 *  Created by Sepehr Sadri on 9/15/19 2:22 AM
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 9/15/19 2:22 AM
 *  sepehrsadri@gmail.com
 *
 */

package sepehr.project.com.androidoreoserviceexample.di.view_model

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import sepehr.project.com.androidoreoserviceexample.di.utils.view_model.ViewModelKey
import sepehr.project.com.androidoreoserviceexample.ui.dashboard.DashboardViewModel

@Module
abstract class DashboardViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    internal abstract fun bindDashboardViewModel(viewModel: DashboardViewModel): ViewModel
}