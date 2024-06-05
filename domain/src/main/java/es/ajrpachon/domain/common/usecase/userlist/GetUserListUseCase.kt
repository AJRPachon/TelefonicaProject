package es.ajrpachon.domain.common.usecase.userlist

import es.ajrpachon.domain.common.models.user.UserBo
import es.ajrpachon.domain.common.repositories.UserListRepository
import es.ajrpachon.domain.common.util.AsyncResult
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