package com.neosoft.composewithcleanarch.domain.repository

interface DeleteUserRepo {

    suspend fun deleteUser(id:Int)
}