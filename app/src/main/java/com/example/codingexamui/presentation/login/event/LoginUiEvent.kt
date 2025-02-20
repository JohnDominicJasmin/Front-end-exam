package com.example.codingexamui.presentation.login.event

sealed class LoginUiEvent {
    data object LoginSuccess : LoginUiEvent()
    data class UserNameError(val message: String) : LoginUiEvent()
    data class PasswordError(val message: String) : LoginUiEvent()
}