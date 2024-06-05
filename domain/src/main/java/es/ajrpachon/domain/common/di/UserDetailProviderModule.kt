package es.ajrpachon.domain.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import es.ajrpachon.domain.common.repositories.UserDetailRepository
import es.ajrpachon.domain.common.usecase.user.GetUserDetailUseCase
import es.ajrpachon.domain.common.usecase.user.GetUserDetailUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
object UserDetailProviderModule {

    @Provides
    fun getUserDetailUseCaseImpl(repository: UserDetailRepository) =
        GetUserDetailUseCaseImpl(repository) as GetUserDetailUseCase
}