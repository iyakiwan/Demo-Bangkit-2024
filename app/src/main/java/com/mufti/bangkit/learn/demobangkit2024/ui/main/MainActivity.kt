package com.mufti.bangkit.learn.demobangkit2024.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.mufti.bangkit.learn.demobangkit2024.R
import com.mufti.bangkit.learn.demobangkit2024.data.mapper.UserMapper
import com.mufti.bangkit.learn.demobangkit2024.data.response.UserResponse
import com.mufti.bangkit.learn.demobangkit2024.data.retrofit.ApiConfig
import com.mufti.bangkit.learn.demobangkit2024.databinding.ActivityMainBinding
import com.mufti.bangkit.learn.demobangkit2024.model.DataDummy
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { UserAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupWindow()

        setupRecyclerView()
        getUser()
    }

    private fun setupWindow() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupRecyclerView() {
        binding.rvUsers.setHasFixedSize(true)
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        binding.rvUsers.adapter = adapter
    }

    private fun getUser() {
        val client = ApiConfig.getApiService(this@MainActivity).getListUsers("1")

        binding.pvUsers.isVisible = true
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val result = response.body()
                    val dataResult = UserMapper.mapListUserResponseToListUser(result)

                    adapter.submitList(dataResult)
                }
                binding.pvUsers.isVisible = false
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                t.printStackTrace()
                binding.pvUsers.isVisible = false
            }
        })
        adapter.submitList(DataDummy.dataDummyUser())
    }
}
