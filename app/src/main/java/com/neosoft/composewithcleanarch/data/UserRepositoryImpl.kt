package com.neosoft.composewithcleanarch.data

import android.content.Context
import com.neosoft.composewithcleanarch.MyApplication
import com.neosoft.composewithcleanarch.data.database.AppDataBase
import com.neosoft.composewithcleanarch.data.database.UserEntity
import com.neosoft.composewithcleanarch.domain.repository.UserRepository
import dagger.hilt.android.internal.lifecycle.HiltViewModelMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject


class UserRepositoryImpl : UserRepository {

    var appDataBase : AppDataBase? = null

    fun initializeDB(context: Context) : AppDataBase {
        return AppDataBase.getDataBaseClient(context)
    }
    override suspend fun saveUserData(id : Int, name: String) {
        appDataBase = initializeDB(MyApplication.getAppInstance())
        CoroutineScope(IO).launch {
            val user_data = UserEntity(id,name)
            appDataBase!!.userDao().insertData(user_data)
        }
    }



}