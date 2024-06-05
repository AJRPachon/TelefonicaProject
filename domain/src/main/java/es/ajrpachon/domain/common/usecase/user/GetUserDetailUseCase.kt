package es.ajrpachon.domain.common.usecase.user

import es.ajrpachon.domain.common.models.user.UserBo
import es.ajrpachon.domain.common.repositories.UserDetailRepository
import es.ajrpachon.domain.common.util.AsyncResult
import kotlinx.coroutines.flow.Flow

interface GetUserDetailUseCase {
    suspend operator fun invoke(id: String) : Flow<AsyncResult<UserBo>>

}

class GetUserDetailUseCaseImpl(
    private val repository: UserDetailRepository
) : GetUserDetailUseCase {
    override suspend fun invoke(id: String): Flow<AsyncResult<UserBo>> {
        return repository.getUserDetail(id).flow()
    }


}