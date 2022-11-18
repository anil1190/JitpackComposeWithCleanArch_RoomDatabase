package com.neosoft.composewithcleanarch.data.repository

import android.content.Context
import com.neosoft.composewithcleanarch.MyApplication
import com.neosoft.composewithcleanarch.data.database.AppDataBase
import com.neosoft.composewithcleanarch.domain.repository.DeleteUserRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class DeleteUserRepoImpl :  DeleteUserRepo{

    var appDataBase : AppDataBase? = null

    fun initializeDB(context: Context) : AppDataBase {
        return AppDataBase.getDataBaseClient(context)
    }

    override suspend fun deleteUser(id: Int) {
        appDataBase = initializeDB(MyApplication.getAppInstance())

        CoroutineScope(IO).launch {
            appDataBase!!.userDao().deleteUser(id)
        }

    }
}