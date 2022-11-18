package com.neosoft.composewithcleanarch.presentation.screens.registration

import android.content.Intent
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neosoft.composewithcleanarch.MyApplication
import com.neosoft.composewithcleanarch.R
import com.neosoft.composewithcleanarch.domain.usecase.SaveUserData
import com.neosoft.composewithcleanarch.presentation.screens.homeScreen.MainActivity
import com.neosoft.composewithcleanarch.presentation.screens.loginScreen.LoginActivity
import com.neosoft.composewithcleanarch.presentation.screens.loginScreen.NAME
import com.neosoft.composewithcleanarch.presentation.screens.loginScreen.PASSWORD
import com.neosoft.composewithcleanarch.presentation.utils.FocusedTextFieldKey
import com.neosoft.composewithcleanarch.presentation.utils.InputValidator
import com.neosoft.composewithcleanarch.presentation.utils.InputWrapper
import com.neosoft.composewithcleanarch.presentation.utils.ScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


const val EMP_ID   = "empId"
@HiltViewModel
class RegistrationViewModel @Inject constructor(private val handle: SavedStateHandle,
                                                private val saveUserData: SaveUserData) : ViewModel() {

    val name = handle.getStateFlow(NAME, InputWrapper())
    val password = handle.getStateFlow(PASSWORD, InputWrapper())
    val emp_id   = handle.getStateFlow(EMP_ID,InputWrapper())
    val areInputsValid = combine(name, password,emp_id) { name, password, emp_id ->
        name.value.isNotEmpty() && name.errorId == null &&
                password.value.isNotEmpty() && password.errorId == null && emp_id.value.isNotEmpty() && emp_id.errorId == null
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)
    private var focusedTextField = handle["focusedTextField"] ?: FocusedTextFieldKey.NAME
        set(value) {
            field = value
            handle["focusedTextField"] = value
        }

    private val _events = Channel<ScreenEvent>()
    val events = _events.receiveAsFlow()

    init {
        // if (focusedTextField != FocusedTextFieldKey.NONE) focusOnLastSelectedTextField()
    }

    fun onNameEntered(input: String) {
        val errorId = InputValidator.getNameErrorIdOrNull(input)
        handle[NAME] = name.value.copy(value = input, errorId = errorId)
    }

    fun onPasswordEntered(input: String) {
        val errorId = InputValidator.getPasswordErrorIdOrNull(input)
        handle[PASSWORD] = password.value.copy(value = input, errorId = errorId)
    }

    fun onEmpIdEntered(input: String) {
        val errorId = InputValidator.getEmpIddErrorIdOrNull(input)
        handle[EMP_ID] = emp_id.value.copy(value = input, errorId = errorId)
    }

    fun onTextFieldFocusChanged(key: FocusedTextFieldKey, isFocused: Boolean) {
        focusedTextField = if (isFocused) key else FocusedTextFieldKey.NONE
    }

    fun onNameImeActionClick() {
        _events.trySend(ScreenEvent.MoveFocus())
    }

    fun onContinueClick() {
        viewModelScope.launch(Dispatchers.Default) {
            if (areInputsValid.value) clearFocusAndHideKeyboard()
            val resId = if (areInputsValid.value) R.string.success else R.string.validation_error
            if (resId.equals(R.string.success)){
                saveUserData.saveUser(emp_id.value.value.toInt(),name = name.value.value)
                val intent = Intent(MyApplication.getAppInstance(), LoginActivity::class.java)
                MyApplication.getAppInstance().startActivity(intent)
            }
            _events.send(ScreenEvent.ShowToast(resId))
        }
    }



    private suspend fun clearFocusAndHideKeyboard() {
        _events.send(ScreenEvent.ClearFocus)
        _events.send(ScreenEvent.UpdateKeyboard(false))
        focusedTextField = FocusedTextFieldKey.NONE
    }

    private fun focusOnLastSelectedTextField() {
        viewModelScope.launch(Dispatchers.Default) {
            _events.send(ScreenEvent.RequestFocus(focusedTextField))
            delay(250)
            _events.send(ScreenEvent.UpdateKeyboard(true))
        }
    }

}