package com.ebha.adminapp

import android.app.DownloadManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ebha.Utils.Utils
import com.ebha.Utils.VolleyWebServices
import java.io.StringReader

class RegisterActivity : AppCompatActivity() {
    lateinit var first_name:EditText
    lateinit var last_name:EditText
    lateinit var email:EditText
    lateinit var password:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        first_name=findViewById(R.id.nombresED) as EditText
        last_name=findViewById(R.id.apellidosED) as EditText
        email=findViewById(R.id.usernameED) as EditText
        password=findViewById(R.id.passwordED) as EditText
        val loginLinkButton:TextView = findViewById(R.id.loginLink) as TextView
        loginLinkButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        val registerButton: Button = findViewById(R.id.registerB) as Button
        registerButton.setOnClickListener {
            if(Utils.verifyEditText(findViewById(R.id.registerView))){
                Log.println(Log.DEBUG,"hola","entrre")
                val params=genParams()
                VolleyWebServices.insertService(VolleyWebServices.URL+"insertUser.php",params,this)
            }else{
                Toast.makeText(this,"Rellene los campos", Toast.LENGTH_SHORT).show()
            }
        }

    }
    fun genParams():MutableMap<String,String>{
        val params = mutableMapOf<String, String>()
        params.put("first_name",first_name.text.toString())
        params.put("last_name",last_name.text.toString())
        params.put("email",email.text.toString())
        params.put("password",password.text.toString())
        return params
    }

}