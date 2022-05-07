package com.example.proyecto_poi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.proyecto_poi.Session.SessionManager
import org.json.JSONObject


class Login : AppCompatActivity() {

    private  var sessionManager = SessionManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val btn_login = findViewById<Button>(R.id.btn_login)
        val btn_register = findViewById<Button>(R.id.btn_register)
        val txt_correo = findViewById<EditText>(R.id.editTextCorreo)
        val txt_pwd = findViewById<EditText>(R.id.editTextPwd)

        sessionManager.SessionManager(applicationContext)

        btn_login.setOnClickListener{

            var correo_ = txt_correo.getText().toString()
            var pwd_ = txt_pwd.getText().toString()

            if(TextUtils.isEmpty(correo_) && TextUtils.isEmpty(pwd_)){
                txt_correo.setError("Your message")

            }else{

                val queue = Volley.newRequestQueue(this)
                val url = "https://192.168.0.11/PSM/login_inc.php"
                val datos = HashMap<String, Any>()
                datos["correo"] = correo_
                datos["pwd"] = pwd_

                val datos_toSend = JSONObject(datos as Map<*, *>?)

                val solicitud = JsonObjectRequest(
                    Request.Method.POST, url, datos_toSend,
                    {response->

                        try{
                            val error_serv = response.getInt("error")
                            if(error_serv == 0){
                                Toast.makeText(this, "Exito. ${response.getString("mensaje")}", Toast.LENGTH_SHORT).show()
                                sessionManager.CreateLoginSession(correo_,pwd_)
                                val lanzar = Intent(this, MainActivity::class.java)
                                startActivity(lanzar)
                            }else{
                                Toast.makeText(this, "Error. ${response.getString("mensaje")}", Toast.LENGTH_SHORT).show()

                            }
                        }catch (e: Exception){
                            Toast.makeText(this, "$e", Toast.LENGTH_SHORT).show()
                        }

                    },{
                        Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
                    })

                HttpsTrustManager.allowAllSSL()
                queue.add(solicitud)

            }


        }
        btn_register.setOnClickListener{
            val lanzar = Intent(this, RegisterActivity::class.java)
            startActivity(lanzar)
        }

    }



}