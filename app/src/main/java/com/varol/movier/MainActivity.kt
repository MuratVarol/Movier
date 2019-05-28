package com.varol.movier

import android.os.Bundle
import com.varol.movier.base.BaseActivity
import com.varol.movier.databinding.ActivityMainBinding
import com.varol.movier.viewmodel.MainVM


class MainActivity : BaseActivity<MainVM, ActivityMainBinding>(MainVM::class) {
    override val layoutRes: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
}
