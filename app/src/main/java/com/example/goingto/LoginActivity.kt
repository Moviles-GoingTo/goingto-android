package com.example.goingto

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.goingto.controller.activities.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ui(applicationContext)
    }

    private fun ui(context: Context) {
        login_button.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        tvRegister.setOnClickListener( {
            val intento = Intent(context, RegisterActivity::class.java)
            startActivity(intento)
        })
    }
}