package com.demo.jetpack.lifecycle

import android.util.Log
import androidx.lifecycle.*

private const val TAG = "MyLocationListener2"
class MyLocationListener2: LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
   fun connect() {
       Log.d(TAG, "connect: ")
   }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun disconnect() {
        Log.d(TAG, "disconnect: ")
    }
}