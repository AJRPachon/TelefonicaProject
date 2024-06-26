package es.ajrpachon.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import es.ajrpachon.domain.common.util.lifecycle.Event
import es.ajrpachon.navigation.NavigationCommand

abstract class BaseViewModel : ViewModel() {

    //For navigation
    private val navigation = MutableLiveData<Event<NavigationCommand>>()

    fun getNavigation() : LiveData<Event<NavigationCommand>> = navigation

    //For error handler
    private val snackbarError = MutableLiveData<Event<String>>()

    fun getSnackbarError() : LiveData<Event<String>> = snackbarError

    /**
     * Convenient method to handle navigation from a [ViewModel]
     */
    fun navigate(directions: NavDirections) {
        navigation.value = Event(NavigationCommand.To(directions))
    }

    fun navigateBack() {
        navigation.value = Event(NavigationCommand.Back)
    }

}