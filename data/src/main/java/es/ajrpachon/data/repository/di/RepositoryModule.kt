package es.ajrpachon.data.repository.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.ajrpachon.data.datasource.UserDetailRemoteDataSource
import es.ajrpachon.data.datasource.UserListRemoteDataSource
import es.ajrpachon.data.repository.userdetail.UserDetailRepositoryImpl
import es.ajrpachon.data.repository.userlist.UserListRepositoryImpl
import es.ajrpachon.domain.common.repositories.UserDetailRepository
import es.ajrpachon.domain.common.repositories.UserListRepository
import es.ajrpachon.domain.common.usecase.dispatchers.AppDispatchers
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun appDispatchersProvider() =
        AppDispatchers(
            Dispatchers.Main,
            Dispatchers.Default,
            Dispatchers.IO
        )

    @Provides
    fun userListRepository(
        remote: UserListRemoteDataSource,
        appdispatchers: AppDispatchers
    ) = UserListRepositoryImpl(
        remote,
        appdispatchers
    ) as UserListRepository

    @Provides
    fun userDetailRepository(
        remote: UserDetailRemoteDataSource,
        appdispatchers: AppDispatchers
    ) = UserDetailRepositoryImpl(
        remote,
        appdispatchers
    ) as UserDetailRepository

}
