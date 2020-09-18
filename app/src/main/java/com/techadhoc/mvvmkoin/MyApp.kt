package com.techadhoc.mvvmkoin

import android.app.Application
import com.techadhoc.mvvmkoin.features.base.startMyKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startMyKoin(this)
    }

}