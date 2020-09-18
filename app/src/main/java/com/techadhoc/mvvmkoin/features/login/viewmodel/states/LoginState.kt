package com.techadhoc.mvvmkoin.features.login.viewmodel.states

sealed class LoginState {
    data class LoginSuccess(val msg: String): LoginState()
    data class LoginError(val msg: String): LoginState()
}