package com.example.proyecto_poi

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class dbh {

    private var queue : RequestQueue
    private val context : Context;

    private val url_login = "https://192.168.0.13/PSM/login_inc.php"
    private val url_register = "https://192.168.0.13/PSM/login_inc.php"

    constructor(context_:Context){
        context = context_
        queue = Volley.newRequestQueue(context_)
    }

    public fun login (correo:String, pwd: String): Int {
        val datos = HashMap<String, Any>()
        datos["correo"] = correo
        datos["pwd"] = pwd

        val datos_toSend = JSONObject(datos as Map<String, Any>?)

        var errorserv : Int =0

        val solicitud = JsonObjectRequest(
            Request.Method.POST, url_login, datos_toSend,
            {response->

                try{
                    errorserv = response.getInt("error")
                    if(errorserv == 0){
                        Toast.makeText(context, "Exito. ${response.getString("mensaje")}", Toast.LENGTH_SHORT).show()


                    }else{
                        Toast.makeText(context, "Error. ${response.getString("mensaje")}", Toast.LENGTH_SHORT).show()

                    }
                }catch (e: Exception){
                    Toast.makeText(context, "$e", Toast.LENGTH_SHORT).show()
                }

            },{
                Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
            })

        HttpsTrustManager.allowAllSSL()
        queue.add(solicitud)
        solicitud.body
        return errorserv
    }



}