package es.ajrpachon.ui.userdetail.viewmodel

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.ajrpachon.domain.common.models.user.UserBo
import es.ajrpachon.domain.common.usecase.dispatchers.AppDispatchers
import es.ajrpachon.domain.common.usecase.user.GetUserDetailUseCase
import es.ajrpachon.domain.common.util.AsyncResult
import es.ajrpachon.domain.common.util.lifecycle.MutableSourceLiveData
import es.ajrpachon.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserUseCase: GetUserDetailUseCase,
    private val dispatchers: AppDispatchers
) : BaseViewModel() {


    private var userDetailLiveData = MutableSourceLiveData<AsyncResult<UserBo>>(dispatchers)

    fun getUserDetailLiveData() = userDetailLiveData.liveData()

    fun requestUserList(id : String) {
        viewModelScope.launch(dispatchers.io) {
            userDetailLiveData.changeSource(
                getUserUseCase.invoke(id).asLiveData(coroutineContext)
            )
        }
    }

}