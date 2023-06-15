package com.example.pasin_app.utils

import android.content.Context
import android.content.SharedPreferences

object sharedPreferences {

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

    fun saveName(name: String, context: Context){
        val editor = editor(context, "onSignIn")
        editor.putString("name", name)
        editor.apply()
    }

    fun saveUmur(umur: String, context: Context){
        val editor = editor(context, "onSignIn")
        editor.putString("umur", umur)
        editor.apply()
    }

    fun savePinggul(pinggul: String, context: Context){
        val editor = editor(context, "onSignIn")
        editor.putString("pinggul", pinggul)
        editor.apply()
    }

    fun saveBahu(bahu: String, context: Context){
        val editor = editor(context, "onSignIn")
        editor.putString("bahu", bahu)
        editor.apply()
    }

    fun saveTinggi(tinggi: String, context: Context){
        val editor = editor(context, "onSignIn")
        editor.putString("tinggi", tinggi)
        editor.apply()
    }

    fun getName(context: Context): String? {
        val sharedPref = context.getSharedPreferences("onSignIn", Context.MODE_PRIVATE)
        return sharedPref.getString("name", null)
    }

    fun getUmur(context: Context): String? {
        val sharedPref = context.getSharedPreferences("onSignIn", Context.MODE_PRIVATE)
        return sharedPref.getString("umur", null)
    }

    fun getPinggul(context: Context): String? {
        val sharedPref = context.getSharedPreferences("onSignIn", Context.MODE_PRIVATE)
        return sharedPref.getString("pinggul", null)
    }

    fun getBahu(context: Context): String? {
        val sharedPref = context.getSharedPreferences("onSignIn", Context.MODE_PRIVATE)
        return sharedPref.getString("bahu", null)
    }

    fun getTinggi(context: Context): String? {
        val sharedPref = context.getSharedPreferences("onSignIn", Context.MODE_PRIVATE)
        return sharedPref.getString("tinggi", null)
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