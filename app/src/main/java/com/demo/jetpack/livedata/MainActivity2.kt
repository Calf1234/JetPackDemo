package com.demo.jetpack.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.demo.jetpack.R
import com.example.oklivedatabus.OkLiveDataBusKt

class MainActivity2 : AppCompatActivity() {
    private val TAG = "MainActivity2"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)


        val data1 = OkLiveDataBusKt.with("data1", String::class.java, false)
        data1
            .observe(this, object : Observer<String> {
                override fun onChanged(t: String?) {
                    Log.d(
                        TAG,
                        "onChanged: data1: ${
                            data1.value
                        }"
                    )
                }

            })


        val data2 = OkLiveDataBusKt.with("data2", String::class.java)
        data2.observe(this, object : Observer<String> {
            override fun onChanged(t: String?) {
                Log.d(
                    TAG,
                    "onChanged: data2: ${
                        data2.value
                    }"
                )
            }

        })

        Thread {
            Thread.sleep(2000)
            // 报错，value主线程执行，postValue可线程执行
//            data2.value = "保留粘性， 数据2222222222"

            data2.postValue("保留粘性， 数据22222222222")
            data1.postValue("去除粘性， 数据222222222222222")
        }.start()



        Thread {
            Thread.sleep(2000)
            data2.postValue("保留粘性， 数据33333333")
            data1.postValue("去除粘性， 数据33333333333333")
        }.start()
    }
}