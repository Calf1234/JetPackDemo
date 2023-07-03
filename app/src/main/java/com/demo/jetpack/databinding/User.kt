package com.demo.jetpack.databinding

import androidx.databinding.ObservableField

class User {
    val nameF: ObservableField<String> by lazy {
        ObservableField<String>()
    }

    val pwdF: ObservableField<String> by lazy {
        ObservableField<String>()
    }
}