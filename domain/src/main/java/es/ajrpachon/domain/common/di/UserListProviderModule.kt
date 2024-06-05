package es.ajrpachon.domain.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import es.ajrpachon.domain.common.repositories.UserListRepository
import es.ajrpachon.domain.common.usecase.userlist.GetUserListUseCase
import es.ajrpachon.domain.common.usecase.userlist.GetUserListUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
object UserListProviderModule {

    @Provides
    fun getUserListUseCaseImpl(repository: UserListRepository) =
        GetUserListUseCaseImpl(repository) as GetUserListUseCase
}
