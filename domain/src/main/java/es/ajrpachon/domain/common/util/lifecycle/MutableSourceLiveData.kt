package es.ajrpachon.domain.common.util.lifecycle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import es.ajrpachon.domain.common.usecase.dispatchers.AppDispatchers
import kotlinx.coroutines.withContext

/**
 * Utility class to facilitate logic to develop when to refresh from a source
 * data is generated a new LiveData in repository. What we get with this class is that
 * the view observes a single LiveData and changes the source every time there is a new one
 * request (for example that the user forces the refresh of data or when there is paging
 * and a new page is added to the existing data).
 */
open class MutableSourceLiveData<Type>(
    private val appDispatchers: es.ajrpachon.domain.common.usecase.dispatchers.AppDispatchers
) : Observer<Type> {

    private val mediatorLiveData = MediatorLiveData<Type?>()
    protected var actualSource: LiveData<Type> = MutableLiveData()

    init {
        mediatorLiveData.addSource(actualSource, this)
    }

    suspend fun changeSource(source: LiveData<Type>) {
        withContext(appDispatchers.main) {
            removeSource(actualSource)
            addSource(source)
            actualSource = source
        }
    }

    fun liveData() = mediatorLiveData as LiveData<Type?>

    protected open fun removeSource(sourceToRemove: LiveData<Type>) {
        mediatorLiveData.removeSource(sourceToRemove)
    }

    protected open fun addSource(sourceToAdd: LiveData<Type>) {
        mediatorLiveData.addSource(sourceToAdd, this)
    }

    override fun onChanged(value: Type?) {
        mediatorLiveData.postValue(value)
    }
}