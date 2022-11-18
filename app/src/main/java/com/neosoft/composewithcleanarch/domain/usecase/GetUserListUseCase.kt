package com.neosoft.composewithcleanarch.domain.usecase

import com.neosoft.composewithcleanarch.data.UserRepositoryImpl
import com.neosoft.composewithcleanarch.domain.IUserModel
import com.neosoft.composewithcleanarch.domain.User
import com.neosoft.composewithcleanarch.domain.repository.GetUserListRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(private val getUserListRepo: GetUserListRepo) {

    fun getUserList() : Flow<List<IUserModel>>{
      return getUserListRepo.getUserList()
   }
}