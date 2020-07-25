package com.ebha.adminapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ebha.Utils.Keys
import com.ebha.Utils.Shared
import com.ebha.Utils.Utils
import com.ebha.Utils.VolleyWebServices

class MainActivity : AppCompatActivity() {
    lateinit var firstname:TextView
    lateinit var lastname:TextView
    lateinit var email:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        firstname= findViewById(R.id.firstname)

        lastname= findViewById(R.id.lastname)

        email= findViewById(R.id.email)


        if (verificarSesion()) {
            Toast.makeText(this, "hay una sesion", Toast.LENGTH_SHORT)
            val sp: SharedPreferences = getSharedPreferences(Keys.keyApp.state, MODE_PRIVATE)
            loadDataUser(VolleyWebServices.URL+"loadDataUser.php?email="+sp.getString(Keys.username.state,""))
            //firstname.text = Shared.firstname
            //lastname.text = Shared.lastname
            //email.text = Shared.email

        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()

        }
    }

    fun verificarSesion(): Boolean {
        // TODO: 23/07/2020 verificar si ya existe una sesion
        val sp: SharedPreferences = getSharedPreferences(Keys.keyApp.state, MODE_PRIVATE )
        return sp.getBoolean(Keys.sesion.state, false)
    }

    fun loadDataUser(URL: String) {
        val credentialRequest = object : JsonArrayRequest(URL, Response.Listener { s ->

           for(row in 0..s.length()-1){

               var jsonObject = s.getJSONObject(row)
               firstname.text = jsonObject.getString("first_name")
               lastname.text = jsonObject.getString("last_name")
               email.text = jsonObject.getString("email")
           }

        }, Response.ErrorListener { e ->
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }) {
            override fun getParams(): Map<String, String> {
                return params
            }

        }
        val requestQueue: RequestQueue = Volley.newRequestQueue(this)
        requestQueue.add(credentialRequest)

    }

}