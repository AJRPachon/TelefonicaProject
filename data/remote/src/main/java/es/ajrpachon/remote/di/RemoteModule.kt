package es.ajrpachon.remote.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.ajrpachon.datasource.UserListRemoteDataSource
import es.ajrpachon.remote.userlist.UserListRemoteDatasourceImpl
import es.ajrpachon.remote.userlist.UserListWs
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule(
    private val endpoint: String = "https://randomuser.me/api/"
) {

    @Provides
    fun interceptorProvider(): Interceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.HEADERS
        }

    @Provides
    fun okHttpClientProvider(interceptor: Interceptor) =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

    @Provides
    fun moshiProvider(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    fun retrofitProvider(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(endpoint)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    fun userListServiceProvider(retrofit: Retrofit): UserListWs =
        retrofit.create(UserListWs::class.java)

    @Provides
    fun userListRemoteDataSourceProvider(userListWs: UserListWs) =
        UserListRemoteDatasourceImpl(userListWs) as UserListRemoteDataSource


}