package com.neosoft.composewithcleanarch.presentation.screens.homeScreen


import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neosoft.composewithcleanarch.data.database.UserEntity
import com.neosoft.composewithcleanarch.domain.IUserModel
import com.neosoft.composewithcleanarch.domain.User
import com.neosoft.composewithcleanarch.domain.usecase.DeleteUserUseCase
import com.neosoft.composewithcleanarch.domain.usecase.GetUserListUseCase
import com.neosoft.composewithcleanarch.domain.usecase.UpdateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val getUserListUseCase: GetUserListUseCase, val updateUserUseCase: UpdateUserUseCase,
                    val deleteUserUseCase: DeleteUserUseCase) : ViewModel(){

    private val _userStateData = MutableStateFlow<Flow<List<IUserModel>>?>(emptyFlow())
    val userStateData = _userStateData.asStateFlow()

    val response : MutableState<List<IUserModel>> = mutableStateOf(listOf())


    init {
        getUserList()
    }


    private fun getUserList(){
        viewModelScope.launch {
            getUserListUseCase.getUserList()
                .catch { e-> Log.d("main","Exception: ${e.message}") }
                .collect{
                    response.value = it
                }
        }
    }

     fun updateUser(id:Int, name: String){
        viewModelScope.launch {
            updateUserUseCase.updateUser(id,name)
        }
    }

    fun deleteUser(id : Int){
        viewModelScope.launch {
            deleteUserUseCase.deleteUser(id)
        }
    }

}