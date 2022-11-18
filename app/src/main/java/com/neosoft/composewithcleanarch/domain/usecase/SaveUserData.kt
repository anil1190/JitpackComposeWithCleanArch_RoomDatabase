package com.neosoft.composewithcleanarch.domain.usecase

import android.content.Context
import com.neosoft.composewithcleanarch.domain.repository.UserRepository
import javax.inject.Inject

class SaveUserData @Inject constructor(private val userRepository: UserRepository) {

   suspend fun saveUser(id : Int,name : String){
       userRepository.saveUserData(id,name)
    }
}