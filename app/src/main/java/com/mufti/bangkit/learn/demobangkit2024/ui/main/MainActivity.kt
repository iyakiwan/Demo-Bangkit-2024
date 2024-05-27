package com.mufti.bangkit.learn.demobangkit2024.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mufti.bangkit.learn.demobangkit2024.R
import com.mufti.bangkit.learn.demobangkit2024.data.Result
import com.mufti.bangkit.learn.demobangkit2024.databinding.ActivityMainBinding
import com.mufti.bangkit.learn.demobangkit2024.model.User
import com.mufti.bangkit.learn.demobangkit2024.ui.form.FormActivity
import com.mufti.bangkit.learn.demobangkit2024.ui.maps.MapsActivity
import com.mufti.bangkit.learn.demobangkit2024.ui.motion.MotionActivity
import com.mufti.bangkit.learn.demobangkit2024.ui.setting.SettingActivity
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
        observerTheme()

        setupView()
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

        binding.rvUsers.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )
        adapter.setOnUserSelected {
            viewModel.setLocalUser(it)
        }
    }

    private fun observerListUser() {
        viewModel.listUser.observe(this) {
            adapter.submitData(lifecycle, it)
        }
    }

    private fun setupView() {
        binding.floatingActionButton.setOnClickListener {
            val dataUser = viewModel.getLocalUser()
            val formatToast = if (dataUser.firstName.isNotEmpty() && dataUser.lastName.isNotEmpty())
                "${dataUser.firstName} ${dataUser.lastName} is selected!"
            else "No user selected!"
            Toast.makeText(this@MainActivity, formatToast, Toast.LENGTH_SHORT).show()
        }

        binding.fabClean.setOnClickListener {
            viewModel.setLocalUser(
                User(
                    id = 0,
                    email = "",
                    firstName = "",
                    lastName = "",
                    avatar = ""
                )
            )
            Toast.makeText(this@MainActivity, "User cleaned!", Toast.LENGTH_SHORT).show()
        }

        binding.fabSetting.setOnClickListener{
            startActivity(Intent(this, SettingActivity::class.java))
        }

        binding.fabCustomView.setOnClickListener {
            startActivity(Intent(this, FormActivity::class.java))
        }

        binding.fabMotion.setOnClickListener {
            startActivity(Intent(this, MotionActivity::class.java))
        }

        binding.fabMaps.setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }
    }

    private fun observerTheme() {
        viewModel.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}
