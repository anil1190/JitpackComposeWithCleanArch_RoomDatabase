package com.neosoft.composewithcleanarch.domain.usecase

import com.neosoft.composewithcleanarch.domain.repository.DeleteUserRepo
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(private val deleteUserRepo: DeleteUserRepo) {

    suspend fun deleteUser(id : Int){
        deleteUserRepo.deleteUser(id)
    }
}