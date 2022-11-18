package com.neosoft.composewithcleanarch.domain.repository

import com.neosoft.composewithcleanarch.data.database.UserEntity
import com.neosoft.composewithcleanarch.domain.IUserModel
import com.neosoft.composewithcleanarch.domain.User
import kotlinx.coroutines.flow.Flow

interface GetUserListRepo {

     fun getUserList() : Flow<List<IUserModel>>
}