package es.ajrpachon.userlist.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import es.ajrpachon.userlist.domain.GetUserListUseCase
import es.ajrpachon.repository.userlist.UserListRepository
import es.ajrpachon.userlist.domain.GetUserListUseCaseImpl


@Module
@InstallIn(ViewModelComponent::class)
object UserListProvideModule {

    @Provides
    fun getUserListUseCaseImpl(repository: UserListRepository) =
        GetUserListUseCaseImpl(repository) as GetUserListUseCase
}