package com.neosoft.composewithcleanarch.data.repository

import android.content.Context
import com.neosoft.composewithcleanarch.MyApplication
import com.neosoft.composewithcleanarch.data.database.AppDataBase
import com.neosoft.composewithcleanarch.domain.repository.UpdateUserRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class UpdateUserRepoImpl : UpdateUserRepo {

    var appDataBase : AppDataBase? = null

    fun initializeDB(context: Context) : AppDataBase {
        return AppDataBase.getDataBaseClient(context)
    }

    override suspend fun updateUser(id: Int, name: String) {
        appDataBase = initializeDB(MyApplication.getAppInstance())

        CoroutineScope(IO).launch {
            appDataBase!!.userDao().updateList(id,name)
        }
    }
}