package com.example.proyecto_poi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.proyecto_poi.Session.SessionManager
import org.json.JSONObject


import java.io.StringReader
import java.net.URL

class RegisterActivity : AppCompatActivity() {


    private  var sessionManager = SessionManager()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        sessionManager.SessionManager(applicationContext)
        val btn_register = findViewById<Button>(R.id.btn_crear)
        var txt_nombre = findViewById<TextView>(R.id.txt_name_reg)
        var txt_apellidos = findViewById<TextView>(R.id.txt_apellidosReg)
        var txt_correo = findViewById<TextView>(R.id.txt_correoReg)
        var txx_telefono = findViewById<TextView>(R.id.txt_telefonoReg)
        var txt_pwd = findViewById<TextView>(R.id.txt_pwd)
        var txt_chkpwd = findViewById<TextView>(R.id.txt_chkpwdReg)


        //sessionManager.SessionManager(applicationContext)

        btn_register.setOnClickListener{
            var nombre_ = txt_nombre.text.toString()
            var apellidos_ = txt_apellidos.text.toString()
            var correo_ = txt_correo.text.toString()
            var telefono_ = txx_telefono.text.toString()
            var pwd_ = txt_pwd.text.toString()
            var chkpwd_ = txt_chkpwd.text.toString()

            if(TextUtils.isEmpty(correo_) && TextUtils.isEmpty(pwd_)){
                txt_correo.setError("Your message")

            }else{

                val queue = Volley.newRequestQueue(this)
                val url_login = "https://192.168.0.5/PSM/login_inc.php"
                val url_register = "https://192.168.0.5/PSM/register_inc.php"

                val datos = HashMap<String, Any>()
                datos["nombre"] = nombre_
                datos["apellidos"] = apellidos_
                datos["correo"] = correo_
                datos["telefono"] = telefono_
                datos["contrasena"] = pwd_
                datos["v_contrasena"] = chkpwd_

                val datos_toSend = JSONObject(datos as Map<*, *>?)

                val solicitud = JsonObjectRequest(
                    Request.Method.POST, url_register, datos_toSend,
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

    }

}