package es.ajrpachon.common.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.google.android.material.snackbar.Snackbar
import es.ajrpachon.common.util.lifecycle.Event

fun Fragment.setupSnackbar(lifecycleOwner: LifecycleOwner, snackbarEvent : LiveData<Event<String>>, timeLength: Int = Snackbar.LENGTH_LONG) {
    snackbarEvent.observe(lifecycleOwner) { event ->
        event.getContentIfNotHandled()?.let { text ->
            context?.let {
                //TODO make snackbar .show(
            }
        }
    }
}