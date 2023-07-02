package com.demo.jetpack.livedata

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService: Service() {
    override fun onBind(intent: Intent?): IBinder? = null
    private val TAG = "MyService"
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: ")
        Thread {
            for (x in 1..100) {
                Log.d(TAG, "onStartCommand: $x send message")
                MyLiveData.info1.postValue("send $x to client")
                Thread.sleep(1000)
            }
        }.start()
        return super.onStartCommand(intent, flags, startId)
    }
}