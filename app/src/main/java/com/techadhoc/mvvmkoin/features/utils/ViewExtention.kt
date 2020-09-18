package com.techadhoc.mvvmkoin.features.utils

import android.app.Activity
import android.app.Dialog
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

private val View.viewSelector: View.(Int) -> View?
    get() = { findViewById(it) }
private val Activity.viewSelector: Activity.(Int) -> View?
    get() = { findViewById(it) }

private val DialogFragment.viewSelector: Activity.(Int) -> View?
    get() = { findViewById(it) }

private val RecyclerView.ViewHolder.viewSelector: RecyclerView.ViewHolder.(Int) -> View?
    get() = { itemView.findViewById(it) }

private val Dialog.viewSelector: Dialog.(Int) -> View?
    get() = { findViewById(it) }

fun <view : View> Activity.findView(id: Int):
        ReadOnlyProperty<Activity, view> {
    return required(id, viewSelector)
}

fun <view : View> View.findView(id: Int):
        ReadOnlyProperty<View, view> {
    return required(id, viewSelector)
}

fun <view : View> View.bindViews(vararg idList: Int):
        ReadOnlyProperty<View, List<view>> {
    return required(idList, viewSelector)
}

fun <view : View> Dialog.findView(id: Int):
        ReadOnlyProperty<Dialog, view> {
    return required(id, viewSelector)


}
@Suppress("UNCHECKED_CAST")
private fun <T, view : View> required(id: Int, find: T.(Int) -> View?) =
    ViewLazyInit { t: T, prop ->
        t.find(id) as view? ?: viewNotFound(id, prop)
    }
@Suppress("UNCHECKED_CAST")
private fun <T, view : View> required(idArray: IntArray, find: T.(Int) -> View?) =
    ViewLazyInit { t: T, prop ->
        idArray.map {
            t.find(it) as view? ?: viewNotFound(it, prop)
        }
    }


private fun viewNotFound(id: Int, prop: KProperty<*>): Nothing =
    throw IllegalStateException("view id $id with '${prop.name}' not found error")


class ViewLazyInit<T, view>(private val initialize: (T, KProperty<*>) -> view) :
    ReadOnlyProperty<T, view>, LifecycleObserver {
    private object BASEOBJECT

    private var value: Any? = BASEOBJECT
    private var addToLifecycleOwner = false
    override fun getValue(thisRef: T, property: KProperty<*>): view {
        verifyLifecycleOwner(thisRef)
        if (value == BASEOBJECT) {
            value = initialize(thisRef, property)
        }
        return value as view
    }

    private fun verifyLifecycleOwner(recv: T) {
        if (!addToLifecycleOwner && recv is LifecycleOwner) {
            recv.lifecycle.addObserver(this)
            addToLifecycleOwner = true
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        value = BASEOBJECT
    }
}