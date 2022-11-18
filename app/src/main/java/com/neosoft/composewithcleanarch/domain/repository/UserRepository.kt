package com.neosoft.composewithcleanarch.domain.repository

import android.content.Context
import dagger.Provides

interface UserRepository {

    suspend fun saveUserData(id : Int, name : String)
}