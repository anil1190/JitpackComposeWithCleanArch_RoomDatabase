package com.neosoft.composewithcleanarch.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.neosoft.composewithcleanarch.domain.IUserModel
import com.neosoft.composewithcleanarch.domain.User

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="Id") override val id : Int,
    @ColumnInfo(name = "userName") override val name: String
) : IUserModel
