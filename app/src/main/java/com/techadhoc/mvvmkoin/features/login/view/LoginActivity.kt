package com.techadhoc.mvvmkoin.features.login.view

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.techadhoc.mvvmkoin.R
import com.techadhoc.mvvmkoin.features.login.viewmodel.LoginViewModel
import com.techadhoc.mvvmkoin.features.login.viewmodel.states.LoginState
import com.techadhoc.mvvmkoin.features.utils.findView
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private val mainText: TextView by findView(R.id.text)
    private val viewModel: LoginViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initObserver()
        // print msg from rep sample
        viewModel.printMsg()
    }

    private fun initObserver() {
        viewModel.loginStateView.observe(this, androidx.lifecycle.Observer {
            it?.let {
                when (it) {
                    is LoginState.LoginSuccess -> loginSuccess(it.msg)

                    is LoginState.LoginError -> loginError(it.msg)
                }
            }
        }
        )
    }

    private fun loginSuccess(msg: String) {
        mainText.text = msg
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    private fun loginError(errorMsg: String) {
        mainText.text = errorMsg
        Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
    }
}