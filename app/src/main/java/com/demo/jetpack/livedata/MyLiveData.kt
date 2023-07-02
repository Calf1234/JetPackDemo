package com.demo.jetpack.livedata

import androidx.lifecycle.MutableLiveData

// object 单例
object MyLiveData {
    // lazy 懒加载， 用到才初始化
    val info1: MutableLiveData<String> by lazy {
        MutableLiveData()
    }
}