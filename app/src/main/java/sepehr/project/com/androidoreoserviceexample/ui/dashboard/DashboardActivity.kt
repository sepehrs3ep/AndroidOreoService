package sepehr.project.com.androidoreoserviceexample.ui.dashboard

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import sepehr.project.com.androidoreoserviceexample.R
import sepehr.project.com.androidoreoserviceexample.databinding.AskForExitDialogBinding
import sepehr.project.com.androidoreoserviceexample.di.utils.view_model.ViewModelProviderFactory
import javax.inject.Inject

class DashboardActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    lateinit var viewModel: DashboardViewModel

    lateinit var exitDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        setupViewModel()

        setupObservers()
    }


    override fun onBackPressed() {
        val dialogBinding = AskForExitDialogBinding.inflate(LayoutInflater.from(this)).apply {
            this.dashboardViewModel = viewModel
            this.lifecycleOwner = this@DashboardActivity
        }

        exitDialog = Dialog(this).apply {
            setContentView(dialogBinding.root)
            setCanceledOnTouchOutside(true)
            show()
        }
    }

    private fun setupViewModel() {
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(DashboardViewModel::class.java)
    }

    private fun setupObservers() {
        viewModel.finishActivityLiveEvent.observe(
            this,
            androidx.lifecycle.Observer {
                it?.let {
                    if (it) {
                        this.finish()
                    }
                }
            }
        )

        viewModel.dismissDialogLiveEvent.observe(
            this,
            androidx.lifecycle.Observer {
                it?.let {
                    if (
                        it &&
                        ::exitDialog.isInitialized
                    ) {
                        this.exitDialog.dismiss()
                    }
                }
            }
        )
    }
}
