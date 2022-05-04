package com.example.proyecto_poi

import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class dbh {

    var queue : RequestQueue

    private val url_login = "https://192.168.1.68/PSM/login_inc.php"
    private val url_register = "https://192.168.1.68/PSM/login_inc.php"

    constructor(context:RequestQueue){
        queue = context
    }

    fun login (datos_ : HashMap<String, Any>){
        datos_
    }



}