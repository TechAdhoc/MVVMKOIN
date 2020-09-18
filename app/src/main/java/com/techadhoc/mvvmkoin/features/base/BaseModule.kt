package com.techadhoc.mvvmkoin.features.base

import com.techadhoc.mvvmkoin.repository.Repository
import com.techadhoc.mvvmkoin.repository.RepositoryInterface
import org.koin.dsl.module

val appModule = module {
    // single instance of Repository
    single<RepositoryInterface> { Repository() }

    // Simple class
    // by inject()
    // factory {  }

    // Simple viewmodel Factory
    // use by viewModel() to inject
}