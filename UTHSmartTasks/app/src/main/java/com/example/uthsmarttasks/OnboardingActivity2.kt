package com.example.uthsmarttasks

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class OnboardingActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding2)

        val backButton: Button = findViewById(R.id.btn_back2)
        val nextButton: Button = findViewById(R.id.btn_next2)

        backButton.setOnClickListener {
            finish()
        }

        nextButton.setOnClickListener {
            val intent = Intent(this, OnboardingActivity3::class.java)
            startActivity(intent)
        }
    }
}
