package es.ajrpachon.domain.common.usecase.userlist

import es.ajrpachon.domain.common.models.user.UserBo
import es.ajrpachon.domain.common.repositories.UserListRepository
import es.ajrpachon.domain.common.util.AsyncResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val repository: UserListRepository
) {
    suspend fun invoke(page: Int): Flow<AsyncResult<List<UserBo>>> {
        return repository.getUserList(page).flow()
    }
}