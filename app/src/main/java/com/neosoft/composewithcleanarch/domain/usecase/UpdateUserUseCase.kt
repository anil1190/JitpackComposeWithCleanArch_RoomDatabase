package com.neosoft.composewithcleanarch.domain.usecase

import com.neosoft.composewithcleanarch.domain.repository.UpdateUserRepo
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(private val updateUserRepo: UpdateUserRepo) {

    suspend fun updateUser(id : Int, name : String){
        updateUserRepo.updateUser(id,name)
    }
}