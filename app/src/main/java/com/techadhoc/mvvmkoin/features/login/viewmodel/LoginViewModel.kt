package com.techadhoc.mvvmkoin.features.login.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.techadhoc.mvvmkoin.features.base.BaseViewModel
import com.techadhoc.mvvmkoin.features.login.viewmodel.interactor.LoginInteractor
import com.techadhoc.mvvmkoin.features.login.viewmodel.states.LoginState
import kotlinx.coroutines.launch

class LoginViewModel(private val interactor: LoginInteractor) : BaseViewModel() {
    private val loginState: MutableLiveData<LoginState> = MutableLiveData()
    val loginStateView: LiveData<LoginState> = loginState
    fun printMsg() {
        launch {
            try {
                Log.d("Hello:", interactor.giveHello())
                val data = interactor.giveHello()
                val finalMsg = "Hello: $data"
                loginState.value = LoginState.LoginSuccess(finalMsg)
            } catch (ex: Exception) {
                showError(ex)
            }
        }
    }

    private fun showError(exception: Exception) {
        loginState.value = LoginState.LoginError(exception.message.toString())
    }

}