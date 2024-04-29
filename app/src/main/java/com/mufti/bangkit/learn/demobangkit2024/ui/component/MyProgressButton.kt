package com.mufti.bangkit.learn.demobangkit2024.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.mufti.bangkit.learn.demobangkit2024.databinding.LayoutProgressButtonViewBinding

class MyProgressButton : ConstraintLayout {

    private lateinit var binding: LayoutProgressButtonViewBinding
    private var buttonText: String = ""
    private var buttonClickAction: () -> Unit = {}

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        binding = LayoutProgressButtonViewBinding.inflate(LayoutInflater.from(context), this, true)
        binding.button.setOnClickListener {
            buttonClickAction()
        }
    }

    fun setButtonText(text: String) {
        binding.button.text = text
        buttonText = text
    }

    fun startLoading(text: String) {
        binding.progressBar.visibility = View.VISIBLE
        binding.button.isEnabled = false
        binding.button.text = text
    }

    fun stopLoading() {
        binding.progressBar.visibility = View.GONE
        binding.button.isEnabled = true
        binding.button.text = buttonText
    }

    fun addOnButtonClickListener(onClick: () -> Unit) {
        buttonClickAction = onClick
    }
}