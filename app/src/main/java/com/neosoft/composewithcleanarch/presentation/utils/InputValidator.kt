package com.neosoft.composewithcleanarch.presentation.utils

import com.neosoft.composewithcleanarch.R


object InputValidator {

    fun getNameErrorIdOrNull(input: String): Int? {
        return when {
            input.length < 2 -> R.string.name_too_short
            //etc..
            else -> null
        }
    }

    fun getPasswordErrorIdOrNull(input: String): Int? {
        return when {
            input.length < 4 -> R.string.password_too_short
            //etc..
            else -> null
        }
    }

    fun getEmpIddErrorIdOrNull(input: String): Int? {
        return when {
            input.length < 2 -> R.string.empId_too_short
            //etc..
            else -> null
        }
    }

}
