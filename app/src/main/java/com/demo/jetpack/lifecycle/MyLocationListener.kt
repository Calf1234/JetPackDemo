package com.demo.jetpack.lifecycle

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

private const val TAG = "MyLocationListener"
class MyLocationListener: DefaultLifecycleObserver {

    override fun onStart(owner: LifecycleOwner) {

        Log.d(TAG, "onStart: ")
    }

    override fun onStop(owner: LifecycleOwner) {
        Log.d(TAG, "onStop: ")
    }
}