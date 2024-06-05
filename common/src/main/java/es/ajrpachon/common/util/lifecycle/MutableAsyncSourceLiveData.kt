package es.ajrpachon.common.util.lifecycle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import es.ajrpachon.data.repository.util.AppDispatchers
import es.ajrpachon.repository.util.AsyncResult

class MutableAsyncSourceLiveData<T>(
    appDispatchers: AppDispatchers
) : MutableSourceLiveData<AsyncResult<T>>(appDispatchers) {

    private val mediatorValueLiveData = MediatorLiveData<T?>()

    init {
        mediatorValueLiveData.addSource( actualSource, this)
    }

    fun valueLiveData() = mediatorValueLiveData as LiveData<*>

    override fun removeSource(sourceToRemove: LiveData<AsyncResult<T>>) {
        super.removeSource(sourceToRemove)
        mediatorValueLiveData.removeSource(sourceToRemove)
    }

    override fun addSource(sourceToAdd: LiveData<AsyncResult<T>>) {
        super.addSource(sourceToAdd)
        mediatorValueLiveData.addSource(sourceToAdd, this)
    }

    override fun onChanged(value: AsyncResult<T>?) {
        super.onChanged(value)
        if (value is AsyncResult.Success) {
            mediatorValueLiveData.postValue(value.data)
        }
    }

}
