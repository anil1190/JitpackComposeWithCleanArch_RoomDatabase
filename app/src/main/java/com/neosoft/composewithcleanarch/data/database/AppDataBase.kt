package com.neosoft.composewithcleanarch.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 2)
abstract class AppDataBase : RoomDatabase(){

    abstract fun userDao() : UserDao

    companion object{

        @Volatile
        private var INSTANCE : AppDataBase? = null
        fun getDataBaseClient(context: Context) : AppDataBase {

            if (INSTANCE != null) INSTANCE!!
            synchronized(this){
                INSTANCE = Room
                    .databaseBuilder(context,AppDataBase::class.java,"UserDataBase")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()

                return INSTANCE!!
            }

        }
    }
}