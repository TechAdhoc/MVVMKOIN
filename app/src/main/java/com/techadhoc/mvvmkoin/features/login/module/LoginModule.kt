package com.techadhoc.mvvmkoin.features.login.module

import com.techadhoc.mvvmkoin.features.login.viewmodel.LoginViewModel
import com.techadhoc.mvvmkoin.features.login.viewmodel.interactor.LoginInteractor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    viewModel { LoginViewModel(get()) }
}

val loginModuleInteractor = module {
    factory { LoginInteractor(get()) }
}


