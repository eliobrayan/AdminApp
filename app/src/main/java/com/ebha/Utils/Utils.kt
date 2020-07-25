package com.ebha.Utils

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.ebha.adminapp.R

class Utils {
    companion object{
        fun getViewChildren(v: View):MutableList<View>{
            val vg: ViewGroup =   v as ViewGroup
            var result:MutableList<View> = mutableListOf()
            if(vg.childCount>0){
                for (i in 0..vg.childCount-1){
                    val child: View = vg.getChildAt(i)
                    result.add(child)
                }
            }

            return result
        }
        fun verifyEditText(v:View):Boolean{
            var result:Boolean=true
            val children:MutableList<View> = getViewChildren(v)
            for (child in children){
                if(child is EditText){
                    if(child.text.toString()==""){
                        result=false
                    }
                }


            }
            return result
        }


    }
}