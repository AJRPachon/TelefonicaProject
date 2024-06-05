package es.ajrpachon.datatest.repository.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.ajrpachon.data.repository.util.AppDispatchers
import es.ajrpachon.datatest.datasource.UserListRemoteDataSource
import es.ajrpachon.repository.userlist.UserListRepository
import es.ajrpachon.repository.userlist.UserListRepositoryImpl
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun appDispatchersProvider() =
        AppDispatchers(Dispatchers.Main, Dispatchers.Default, Dispatchers.IO)

    @Provides
    fun UserListRepository(
        remote: UserListRemoteDataSource,
        appdispatchers: AppDispatchers
    ) = UserListRepositoryImpl(
        remote,
        appdispatchers
    ) as UserListRepository

}
