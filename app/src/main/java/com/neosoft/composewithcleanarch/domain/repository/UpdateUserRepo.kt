package com.neosoft.composewithcleanarch.domain.repository

interface UpdateUserRepo {

    suspend fun updateUser(id:Int, name : String)
}