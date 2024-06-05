package es.ajrpachon.userlist.domain

import es.ajrpachon.repository.util.AsyncResult
import es.ajrpachon.model.UserBo
import es.ajrpachon.repository.userlist.UserListRepository
import kotlinx.coroutines.flow.Flow

interface GetUserListUseCase {

    suspend operator fun invoke() : Flow<AsyncResult<List<UserBo>>>

}

class GetUserListUseCaseImpl(
    private val repository: UserListRepository
) : GetUserListUseCase {

    override suspend fun invoke(): Flow<AsyncResult<List<UserBo>>> {
        return repository.getUserList().flow()
    }

}