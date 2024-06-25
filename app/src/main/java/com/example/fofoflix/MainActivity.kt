package com.example.fofoflix

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val butLog: Button = findViewById(R.id.btnLoginNew)
        butLog.setOnClickListener {
            val intent: Intent = Intent(this, LoginPage::class.java)
            startActivity(intent)
        }

        val btnReg: Button = findViewById(R.id.btnRegisterNew)
        btnReg.setOnClickListener {
            val intent: Intent = Intent(this, RegisterPage::class.java)
            startActivity(intent)
        }


    }
}