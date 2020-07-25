package com.ebha.Utils


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ebha.adminapp.LoginActivity
import com.ebha.adminapp.MainActivity
import org.json.JSONArray
import org.json.JSONObject

class VolleyWebServices {
    companion object{
        const val URL:String="http://192.168.0.109:80/apugasWS/"

        fun insertService(URL:String,params:MutableMap<String,String>,context:Context){
            val stringRequest = object : StringRequest(Request.Method.POST, URL, Response.Listener { s ->
                Toast.makeText(context,"Succesfull...", Toast.LENGTH_SHORT).show()
            }, Response.ErrorListener { e ->
                Toast.makeText(context,e.toString(), Toast.LENGTH_SHORT).show()
            }) {
                override fun getParams(): Map<String, String>{
                    return params
                }
            }
            val requestQueue: RequestQueue = Volley.newRequestQueue(context)
            requestQueue.add(stringRequest)

        }
        fun validateCredentials(URL: String,params:MutableMap<String,String>,context: Context): Boolean {
            var result:Boolean = false
            val credentialRequest = object : StringRequest(Request.Method.POST, URL, Response.Listener { s ->
                if(s.isNotEmpty()){
                    result=true

                }

            }, Response.ErrorListener { e ->
                Toast.makeText(context,e.toString(), Toast.LENGTH_SHORT).show()
            }) {
                override fun getParams(): Map<String, String>{
                    return params
                }


            }
            val requestQueue: RequestQueue = Volley.newRequestQueue(context)
            requestQueue.add(credentialRequest)
            return result
        }




    }
}