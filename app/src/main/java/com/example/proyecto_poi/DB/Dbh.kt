package com.example.proyecto_poi

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.RequestFuture
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException


class dbh {

    private var queue : RequestQueue
    private val context : Context;

    private val url_login = "https://192.168.0.5/PSM/login_inc.php"
    private val url_register = "https://192.168.0.5/PSM/login_inc.php"
    private var errorserv = 0

    constructor(context_:Context){
        context = context_
        queue = Volley.newRequestQueue(context_)
    }

     fun login (correo:String, pwd: String): Int {
        val datos = HashMap<String, Any>()
        datos["correo"] = correo
        datos["pwd"] = pwd

        val datos_toSend = JSONObject(datos as Map<String, Any>?)

        val future = RequestFuture.newFuture<JSONObject>()


        val solicitud = JsonObjectRequest(
            Request.Method.POST, url_login, datos_toSend,{response->
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


        return errorserv
    }



}