package com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.utils

import android.content.Context
import android.content.SharedPreferences
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.fragments.login.LoginFragment


class Prefs(context: Context) {

    private var PRIVATE_MODE = 0
    var isLoggued= false

    fun getPrefState(context: Context, prefVariable:String):Boolean{
        val prefs: SharedPreferences = context.getSharedPreferences(prefVariable, PRIVATE_MODE)
        isLoggued= prefs.getBoolean(LoginFragment.SHARED_PREFS, false)
      return isLoggued
    }

    fun setPrefState(context: Context, prefVariable:String, isLogguedIn: Boolean){
        val prefs: SharedPreferences = context.getSharedPreferences(prefVariable, PRIVATE_MODE)
        with (prefs.edit()) {
            putBoolean(prefVariable, isLogguedIn)
            apply()
            commit()
        }
    }
}