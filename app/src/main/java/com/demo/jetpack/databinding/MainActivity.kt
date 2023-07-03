package com.demo.jetpack.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.demo.jetpack.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityMain5Binding>(this, R.layout.activity_main5)

        var user = User()
        user.nameF.set("hhaha")
        user.pwdF.set("ajlj")

        binding.user = user
    }
}