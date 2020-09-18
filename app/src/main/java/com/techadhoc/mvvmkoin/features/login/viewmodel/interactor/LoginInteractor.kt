package com.techadhoc.mvvmkoin.features.login.viewmodel.interactor

import com.techadhoc.mvvmkoin.repository.Repository
import com.techadhoc.mvvmkoin.repository.RepositoryInterface

class LoginInteractor(private val repository: RepositoryInterface) {
   suspend fun giveHello() :String {
        return repository.giveHello()
    }
}