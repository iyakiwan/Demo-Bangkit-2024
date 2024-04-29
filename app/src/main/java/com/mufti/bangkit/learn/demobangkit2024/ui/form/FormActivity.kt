package com.mufti.bangkit.learn.demobangkit2024.ui.form

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mufti.bangkit.learn.demobangkit2024.databinding.ActivityFormBinding

class FormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormBinding
    private val viewModel: FormViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupWindow()

        setupForm()

        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                binding.myProgressButton.startLoading("Loading...")
            } else {
                binding.myProgressButton.stopLoading()
            }
        }
    }

    private fun setupWindow() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupForm() {
        // Melakukan pengecekan saat pertama kali activity terbentuk
        setMyButtonEnable()

        // Menambahkan metode ketika text terjadi perubahan
        binding.myEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                setMyButtonEnable()
            }

            override fun afterTextChanged(s: Editable) {

            }
        })

        // Menambahkan aksi klik kepada button
        binding.myButton.setOnClickListener {
            Toast.makeText(
                this@FormActivity,
                binding.myEditText.text,
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.myProgressButton.setButtonText("Submit")

        binding.myProgressButton.addOnButtonClickListener {
            viewModel.setLoading(true)
            Handler(Looper.getMainLooper()).postDelayed({
                viewModel.setLoading(false)
            }, 1500)
        }
    }

    // Metode untuk mengubah disable dan enable pada button
    private fun setMyButtonEnable() {
        val result = binding.myEditText.text
        binding.myButton.isEnabled = result != null && result.toString().isNotEmpty()
    }
}