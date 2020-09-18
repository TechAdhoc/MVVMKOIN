package com.techadhoc.mvvmkoin.features.base

import android.content.Context
import com.techadhoc.mvvmkoin.features.login.module.loginModule
import com.techadhoc.mvvmkoin.features.login.module.loginModuleInteractor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

fun startMyKoin(context: Context) {
    startKoin {
        androidLogger()
        androidContext(context)
        modules(appBaseModules + allFeatureModules + allInteractorModules)
    }
}

val appBaseModules = listOf(appModule)

val allFeatureModules = listOf(loginModule)

val allInteractorModules = listOf(loginModuleInteractor)