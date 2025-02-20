package com.example.codingexamui.presentation.login.event

sealed class LoginEvent {
    data class Login(val userName: String, val password: String) : LoginEvent()
}