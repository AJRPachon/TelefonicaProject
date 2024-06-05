package es.ajrpachon.userlist.ui.viewmodel

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.ajrpachon.common.ui.BaseViewModel
import es.ajrpachon.common.util.lifecycle.MutableSourceLiveData
import es.ajrpachon.data.repository.util.AppDispatchers
import es.ajrpachon.repository.util.AsyncResult
import es.ajrpachon.model.UserBo
import es.ajrpachon.userlist.domain.GetUserListUseCase
import es.ajrpachon.userlist.ui.fragment.UserListFragmentDirections
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase,
    private val dispatchers: AppDispatchers
) : BaseViewModel() {

    private var userListLiveData = MutableSourceLiveData<AsyncResult<List<UserBo>>>(dispatchers)

    fun getUserListLiveData() = userListLiveData.liveData()

    fun requestUserList() {
        viewModelScope.launch(dispatchers.io) {
            userListLiveData.changeSource(
                getUserListUseCase.invoke().asLiveData(coroutineContext)
            )
        }
    }

    fun goToUserDetail(id : String) {
        navigate(UserListFragmentDirections.actionUserlistToNavGraphUserDetail(id.toLong()))
    }
}