package com.hefny.hady.pixabaygallery.modules.main.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hefny.hady.pixabaygallery.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}