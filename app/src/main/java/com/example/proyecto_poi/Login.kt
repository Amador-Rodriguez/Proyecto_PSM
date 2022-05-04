package com.example.proyecto_poi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText


class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val btn_login = findViewById<Button>(R.id.btn_login);
        val btn_register = findViewById<Button>(R.id.btn_register);
        val txt_correo = findViewById<EditText>(R.id.editTextCorreo)
        val txt_pwd = findViewById<EditText>(R.id.editTextPwd)

        val bd = dbh(this)

        btn_login.setOnClickListener{

            var correo_ = txt_correo.getText().toString()
            var pwd_ = txt_pwd.getText().toString()

            if(TextUtils.isEmpty(correo_) && TextUtils.isEmpty(pwd_)){
                txt_correo.setError("Your message");

            }else{
                var error = bd.login(correo_,pwd_)
                if(error == 0){
                    val lanzar = Intent(this, MainActivity::class.java)
                    startActivity(lanzar)
                }

            }


        }
        btn_register.setOnClickListener{
            val lanzar = Intent(this, RegisterActivity::class.java)
            startActivity(lanzar)
        }


    }
}