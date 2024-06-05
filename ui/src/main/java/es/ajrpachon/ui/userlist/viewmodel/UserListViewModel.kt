package es.ajrpachon.ui.userlist.viewmodel

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.ajrpachon.domain.common.models.user.UserBo
import es.ajrpachon.domain.common.usecase.dispatchers.AppDispatchers
import es.ajrpachon.domain.common.usecase.userlist.GetUserListUseCase
import es.ajrpachon.domain.common.util.AsyncResult
import es.ajrpachon.domain.common.util.lifecycle.MutableSourceLiveData
import es.ajrpachon.ui.base.BaseViewModel
import es.ajrpachon.ui.userlist.fragment.UserListFragmentDirections
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase,
    private val dispatchers: AppDispatchers
) : BaseViewModel() {

    private var currentPage = 1

    private var userListLiveData = MutableSourceLiveData<AsyncResult<List<UserBo>>>(dispatchers)

    fun getUserListLiveData() = userListLiveData.liveData()

    fun requestUserList() {
        currentPage++
        viewModelScope.launch(dispatchers.io) {
            userListLiveData.changeSource(
                getUserListUseCase.invoke(currentPage).asLiveData(coroutineContext)
            )
        }
    }

    fun goToUserDetail(id : String) {
        navigate(UserListFragmentDirections.actionUserListToUserDetail(id))
    }
}