package es.ajrpachon.common.ui

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import es.ajrpachon.common.extensions.setupSnackbar
import es.ajrpachon.navigation.NavigationCommand

abstract class BaseFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeNavigation(getViewModel())
        setupSnackbar(this, getViewModel().getSnackbarError(), Snackbar.LENGTH_LONG)
    }

    abstract fun getViewModel(): BaseViewModel

    private fun observeNavigation(viewModel: BaseViewModel) {
        viewModel.getNavigation().observe(viewLifecycleOwner) {
            it?.getContentIfNotHandled()?.let { command ->
                when (command) {
                    is NavigationCommand.To -> findNavController().navigate(
                        command.directions,
                        getExtras()
                    )

                    is NavigationCommand.Back -> findNavController().navigateUp()
                }
            }
        }
    }

    /**
     * When [navGraphId] isn't null, returns a [ViewModel] scoped to the Navigation Graph life
     * cycle. Otherwise, returns a [ViewModel] scoped to this [Fragment] life cycle.
     */
    inline fun <reified T : ViewModel> viewModelBinder(@IdRes navGraphId: Int? = null): Lazy<T> =
        when (navGraphId) {
            null -> viewModels()
            else -> hiltNavGraphViewModels(navGraphId)
        }

    /**
     * [FragmentNavigatorExtras] mainly used to enable Shared Element transitions
     */
    open fun getExtras(): FragmentNavigator.Extras = FragmentNavigatorExtras()

}