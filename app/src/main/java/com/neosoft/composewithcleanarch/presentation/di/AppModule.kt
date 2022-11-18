package com.neosoft.composewithcleanarch.presentation.di

import com.neosoft.composewithcleanarch.data.GetUserRepoImpl
import com.neosoft.composewithcleanarch.data.UserRepositoryImpl
import com.neosoft.composewithcleanarch.data.repository.DeleteUserRepoImpl
import com.neosoft.composewithcleanarch.data.repository.UpdateUserRepoImpl
import com.neosoft.composewithcleanarch.domain.repository.DeleteUserRepo
import com.neosoft.composewithcleanarch.domain.repository.GetUserListRepo
import com.neosoft.composewithcleanarch.domain.repository.UpdateUserRepo
import com.neosoft.composewithcleanarch.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideUserRepositoryData() : UserRepository = UserRepositoryImpl()

    @Provides
    @Singleton
    fun provideGetUserListRepository() : GetUserListRepo = GetUserRepoImpl()

    @Provides
    @Singleton
    fun provideUpdateUserRepository() : UpdateUserRepo = UpdateUserRepoImpl()

    @Provides
    @Singleton
    fun provideDeleteUser() : DeleteUserRepo = DeleteUserRepoImpl()
}