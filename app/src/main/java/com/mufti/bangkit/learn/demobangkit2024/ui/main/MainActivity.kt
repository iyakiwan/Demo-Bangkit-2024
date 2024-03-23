package com.mufti.bangkit.learn.demobangkit2024.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mufti.bangkit.learn.demobangkit2024.R
import com.mufti.bangkit.learn.demobangkit2024.data.Result
import com.mufti.bangkit.learn.demobangkit2024.databinding.ActivityMainBinding
import com.mufti.bangkit.learn.demobangkit2024.utils.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val adapter by lazy { UserAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupWindow()

        setupViewModel()
        setupRecyclerView()

        observerListUser()
    }

    private fun setupWindow() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupViewModel() {
        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)

        viewModel = ViewModelProvider(
            this,
            factory
        )[MainViewModel::class.java]
    }

    private fun setupRecyclerView() {
        binding.rvUsers.setHasFixedSize(true)
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        binding.rvUsers.adapter = adapter
    }

    private fun observerListUser() {
        viewModel.listUser.observe(this){
            when(it){
                is Result.Loading -> {
                    binding.pvUsers.isVisible = true
                }
                is Result.Success -> {
                    binding.pvUsers.isVisible = false
                    adapter.submitList(it.data)
                }
                is Result.Error -> {
                    binding.pvUsers.isVisible = false
                    Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
