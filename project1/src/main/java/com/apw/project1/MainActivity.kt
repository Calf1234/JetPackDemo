package com.apw.project1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.apw.project1.databinding.ProjectMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding =
            DataBindingUtil.setContentView<ProjectMainBinding>(this, R.layout.project_main)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(MainViewModel::class.java)
        dataBinding?.vm = viewModel

        dataBinding!!.lifecycleOwner = this
    }

//    override fun onRetainCustomNonConfigurationInstance(): Any? {
//        return super.onRetainCustomNonConfigurationInstance()
//    }
}