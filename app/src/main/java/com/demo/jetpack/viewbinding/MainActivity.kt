package com.demo.jetpack.viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demo.jetpack.R
import com.demo.jetpack.databinding.ActivityMain4Binding

// ViewBinding 相比DataBinding，是个裁剪版，功能没那么多 框架自然就轻量
// ViewBinding 采用的不是APT注解处理器，是Gradle插件
class MainActivity : AppCompatActivity() {

    var vb: ActivityMain4Binding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main4)

        // findViewById 绑定机制， 出现 访问到当前布局文件之外的view
        // 面向对象 activity_main4布局文件就是一个对象vb
        // build/generated/data_binding_base_class_source_out/debug/out/com/demo/jetpack/databinding/ActivityMain4Binding.java
        vb = ActivityMain4Binding.inflate(layoutInflater)
        setContentView(vb!!.root)

        vb!!.main4Tv1.setText("hahhaha")
    }
}