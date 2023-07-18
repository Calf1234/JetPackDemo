package com.apw.project1

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val phoneInfo by lazy { MutableLiveData<String>() }

    init {
        // 初始化默认值
        phoneInfo.value = ""
    }

    var context: Context = application

    // 输入
    fun appendNumber(number: String) {
        phoneInfo.value = phoneInfo.value + number
    }

    // 删除
    fun backspaceNumber() {
        // ?: 空追加
        var length = phoneInfo.value?.length ?: 0

        if (length > 0) {
            // !! 保证一定能够拿到length
            phoneInfo.value = phoneInfo.value?.substring(0, phoneInfo.value?.length!! - 1)
        }
    }

    fun clear() {
        phoneInfo.value = ""
    }

    fun callPhone() {
        val intent = Intent()
        intent.action = Intent.ACTION_DIAL
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.data = Uri.parse("tel:" + phoneInfo.value)
        context.startActivity(intent)
    }
}