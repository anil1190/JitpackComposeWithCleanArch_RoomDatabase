package com.neosoft.composewithcleanarch.data

import android.content.Context
import com.neosoft.composewithcleanarch.MyApplication
import com.neosoft.composewithcleanarch.data.database.AppDataBase
import com.neosoft.composewithcleanarch.data.database.UserEntity
import com.neosoft.composewithcleanarch.domain.IUserModel
import com.neosoft.composewithcleanarch.domain.User
import com.neosoft.composewithcleanarch.domain.repository.GetUserListRepo
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

class GetUserRepoImpl : GetUserListRepo {
    var appDataBase : AppDataBase? = null

    fun initializeDB(context: Context) : AppDataBase {
        return AppDataBase.getDataBaseClient(context)
    }
    override fun getUserList(): Flow<List<IUserModel>> {
        appDataBase = initializeDB(MyApplication.getAppInstance())
        var uList : Flow<List<IUserModel>>? = null
        uList = appDataBase!!.userDao().getAll()
       /* CoroutineScope(Dispatchers.IO).async {



        }.await()*/

        return uList!!
    }
}