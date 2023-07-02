package com.demo.jetpack.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.demo.jetpack.R

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycle.addObserver(MyLocationListener())
        lifecycle.addObserver(MyLocationListener2())
        lifecycle.addObserver(MyLocationListener3())
    }

    inner class MyLocationListener3 : DefaultLifecycleObserver {

        override fun onStart(owner: LifecycleOwner) {

            Log.d(TAG, "onStart: ")
        }

        override fun onStop(owner: LifecycleOwner) {

            Log.d(TAG, "onStop: ")
        }
    }
}