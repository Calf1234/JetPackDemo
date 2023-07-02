package com.demo.jetpack.livedata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.demo.jetpack.R
import com.example.oklivedatabus.OkLiveDataBusKt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


//        lifecycle.addObserver()

        val textView = findViewById<TextView>(R.id.livedata_tv1)

        val button = findViewById<Button>(R.id.livedata_btn1)
        button.setOnClickListener {
            startService(Intent(this, MyService::class.java))
            Toast.makeText(this, "service start ok", Toast.LENGTH_LONG).show()
        }

        // 观察者, lamda表达式
        MyLiveData.info1.observe(this, {
            // 在active状态才会更新view，即至少是onstart
            textView.text = it
//            Toast.makeText(this, "$it from service", Toast.LENGTH_LONG).show()
            Toast.makeText(this@MainActivity, "$it from service", Toast.LENGTH_LONG).show()
        })

        /* // object表达式 代替new
         MyLiveData.info1.observe(this, object: Observer<String> {
             override fun onChanged(t: String?) {
                 TODO("Not yet implemented")
             }
         })*/

        Thread {
            Thread.sleep(2000)
            MyLiveData.info1.postValue("2s后 修改了值")
        }.start()

        Thread {
            Thread.sleep(5000)
            MyLiveData.info1.postValue("5s后 修改了值")
        }.start()

        /*// livedata
        livedata 利用lifecycle， 被观察者 页面或者service 监听生命周期
            一侧postValue，另一侧监听到Value变化 在页面或者service active下更新视图数据

        先订阅     后setValue       正常情况
        先setValue       后订阅     居然也收到了数据 这就是 粘性数据*/

        val button2 = findViewById<Button>(R.id.livedata_btn2)
        button2.setOnClickListener {
            OkLiveDataBusKt.with("data1", String::class.java, false).value = "去除粘性 数据111111"
            OkLiveDataBusKt.with("data2", String::class.java).value = "启用粘性 数据111111"
            startActivity(Intent(this, MainActivity2::class.java))
        }

    }
}