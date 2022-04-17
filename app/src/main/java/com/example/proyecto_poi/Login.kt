package com.example.proyecto_poi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val btn_login = findViewById<Button>(R.id.btn_login);
        val btn_register = findViewById<Button>(R.id.btn_register);

        btn_login.setOnClickListener{
            val lanzar = Intent(this, MainActivity::class.java)
            startActivity(lanzar)
        }
        btn_register.setOnClickListener{
            val lanzar = Intent(this, RegisterActivity::class.java)
            startActivity(lanzar)
        }


    }
}