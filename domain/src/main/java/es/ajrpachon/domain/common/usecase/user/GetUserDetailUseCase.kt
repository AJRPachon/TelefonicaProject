package es.ajrpachon.domain.common.usecase.user

import es.ajrpachon.domain.common.models.user.UserBo
import es.ajrpachon.domain.common.repositories.UserDetailRepository
import es.ajrpachon.domain.common.util.AsyncResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(
    private val repository: UserDetailRepository
) {
    suspend fun invoke(uuid: String): Flow<AsyncResult<UserBo>> {
        return repository.getUserDetail(uuid).flow()
    }


}