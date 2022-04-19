package com.example.proyecto_poi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import org.json.JSONObject

import java.io.StringReader

class RegisterActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    private fun insertar (){
        val url = "http://localhost/db_psm/register_inc.php";
        val datos = HashMap<String, Any>()
        val txt_name = findViewById<View>(R.id.txt_name) as EditText
        val txt_pwd = findViewById<View>(R.id.txt_pwd) as EditText

        data["nombre"] = txt_name.text.toString()
        data["contrasena"] = txt_pwd.text.toString()

        val datos_enviar = JSONObject(datos as Map<*, *>?)


    }

}