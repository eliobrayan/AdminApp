package com.ebha.adminapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ebha.Utils.Keys
import com.ebha.Utils.Shared
import com.ebha.Utils.Utils.Companion.verifyEditText
import com.ebha.Utils.VolleyWebServices

class LoginActivity : AppCompatActivity() {
    lateinit var email:EditText
    lateinit var password:EditText
    lateinit var isCheckedRememberSession:Switch
    var validSession:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email=findViewById(R.id.usernameED)
        password=findViewById(R.id.passwordED)
        isCheckedRememberSession=findViewById(R.id.rememberAccountS)
        val registerLinkButton: TextView = findViewById(R.id.registerLink) as TextView
        registerLinkButton.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }

        val loginButton:Button= findViewById(R.id.loginB) as Button
        loginButton.setOnClickListener {
            if(verifyEditText(findViewById(R.id.loginView))){
                val main=MainActivity()
                validateCredentials(VolleyWebServices.URL+"loginUser.php",genParams())


            }else{
                Toast.makeText(this,"Rellene los campos",Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun genParams():MutableMap<String,String>{
        val params = mutableMapOf<String, String>()
        params.put("email",email.text.toString())
        params.put("password",password.text.toString())
        return params
    }
    fun validateCredentials(URL: String, params:MutableMap<String,String>) {

        val credentialRequest = object : StringRequest(Request.Method.POST, URL, Response.Listener { s ->

            if(s.toString()=="yes"){
                //Toast.makeText(this,s.toString(), Toast.LENGTH_SHORT).show()
                val sp: SharedPreferences = getSharedPreferences(Keys.keyApp.state, MODE_PRIVATE)
                val editor=sp.edit()
                editor.putBoolean(Keys.sesion.state,true)
                editor.putString(Keys.username.state,email.text.toString())
                editor.apply()
                Toast.makeText(this, sp.getBoolean(Keys.sesion.state,false).toString(), Toast.LENGTH_SHORT).show()

                startActivity(Intent(this,MainActivity::class.java))
            }else{
                Toast.makeText(this,"Credenciales equivocadas", Toast.LENGTH_SHORT).show()
            }

        }, Response.ErrorListener { e ->
            Toast.makeText(this,e.toString(), Toast.LENGTH_SHORT).show()
        }) {
            override fun getParams(): Map<String, String>{
                return params
            }


        }
        val requestQueue: RequestQueue = Volley.newRequestQueue(this)
        requestQueue.add(credentialRequest)

    }

}