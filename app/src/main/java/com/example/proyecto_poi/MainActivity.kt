package com.example.proyecto_poi


import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast

import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import com.example.proyecto_poi.fragments.CategoriasFragment
import com.example.proyecto_poi.fragments.HomeFragment
import com.example.proyecto_poi.fragments.MessagesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.jetbrains.annotations.NonNls
import androidx.appcompat.widget.Toolbar
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.proyecto_poi.Session.SessionManager
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavView : BottomNavigationView
    private lateinit var topBar : Toolbar

    private val homeFragment = HomeFragment()
    private val categoriasFragment = CategoriasFragment()
    private val messagesFragment = MessagesFragment()

    private  var sessionManager = SessionManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(homeFragment)
        topBar = findViewById(R.id.topBar)
        setSupportActionBar(topBar)
        bottomNavView = findViewById(R.id.bottomNavigation)


        bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    replaceFragment(homeFragment)
                }
                R.id.menu_folder -> {
                    replaceFragment(categoriasFragment)
                }
                R.id.menu_msg -> {
                    replaceFragment(messagesFragment)
                }

            }
            true
        }

        sessionManager.SessionManager(applicationContext)

        sessionManager.checkLogin()



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.new_post -> {
                val lanzar = Intent(this, NewPostActivity::class.java)
                startActivity(lanzar)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu (menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_top,menu)


        return super.onCreateOptionsMenu(menu)
    }

    private fun setFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            commit()
        }
    }

    private fun replaceFragment(fragment: Fragment){
        if(fragment !=  null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }

    override fun onBackPressed() {

    }

}