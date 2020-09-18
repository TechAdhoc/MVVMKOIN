package com.techadhoc.mvvmkoin.features.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {
    private val viwModelSupJob = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + viwModelSupJob

    override fun onCleared() {
        super.onCleared()
        viwModelSupJob.cancelChildren()
    }
}