package com.iacanched.GhibliApp.ui.view.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController

import com.iacanched.GhibliApp.R
import com.iacanched.GhibliApp.base.BaseActivity
import com.iacanched.GhibliApp.ui.viewmodel.AppViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp() = findNavController(R.id.nav_host_fragment).navigateUp()




}