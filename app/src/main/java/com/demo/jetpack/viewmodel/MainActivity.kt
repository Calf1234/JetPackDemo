package com.demo.jetpack.viewmodel

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.demo.jetpack.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)


        val myViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MyViewModel::class.java)


        val text_num = findViewById<TextView>(R.id.text_num)
        text_num.text = "${myViewModel.num}"
        findViewById<Button>(R.id.btn_plus).setOnClickListener {
            text_num.text = "${++myViewModel.num}"
        }


    }
}