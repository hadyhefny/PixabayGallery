package com.hefny.hady.pixabaygallery.modules.main.presentation.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.hefny.hady.pixabaygallery.databinding.ActivityMainBinding
import com.hefny.hady.pixabaygallery.modules.images.presentation.ImagesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val vm: ImagesViewModel by viewModels()
        binding.root.setOnClickListener {
            vm.getImages("fruits")
        }
    }
}