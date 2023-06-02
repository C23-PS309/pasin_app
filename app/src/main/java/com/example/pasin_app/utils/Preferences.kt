package com.example.pasin_app.utils

import android.content.Context
import android.content.SharedPreferences

object Preferences {

    fun init(context: Context, name: String): SharedPreferences {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    private fun editor(context: Context, name: String): SharedPreferences.Editor {
        val sharedPref = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        return sharedPref.edit()
    }

    fun saveImageCamera(image: String, context: Context){
        val editor = editor(context, "onSignIn")
        editor.putString("image", image)
        editor.apply()
    }

    fun deleteImageCamera(context: Context){
        val editor = editor(context, "onSignIn")
        editor.remove("image")
        editor.apply()
    }

    fun saveImageGallery(image: String, context: Context){
        val editor = editor(context, "onSignIn")
        editor.putString("image", image)
        editor.apply()
    }

    fun deleteImageGallery(context: Context){
        val editor = editor(context, "onSignIn")
        editor.remove("image")
        editor.apply()
    }

    fun getImageCamera(context: Context): String? {
        val sharedPref = context.getSharedPreferences("onSignIn", Context.MODE_PRIVATE)
        return sharedPref.getString("image", null)
    }

    fun getImageGallery(context: Context): String? {
        val sharedPref = context.getSharedPreferences("onSignIn", Context.MODE_PRIVATE)
        return sharedPref.getString("image", null)
    }

    fun logout(context: Context){
        val editor = editor(context, "onSignIn")
        editor.remove("token")
        editor.remove("status")
        editor.apply()
    }

}