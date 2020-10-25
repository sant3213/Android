package com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.utils

import android.content.Context
import android.content.SharedPreferences
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.fragments.login.LoginFragment


class Prefs(context: Context) {

    private var PRIVATE_MODE = 0
    val USER_STATE = "userState"
    fun getPrefState(context: Context, prefVariable:String):Boolean{
        val prefs: SharedPreferences = context.getSharedPreferences(prefVariable, PRIVATE_MODE)
      return prefs.getBoolean(USER_STATE, false)
    }

    fun setPrefState(context: Context, prefVariable:String, state: Boolean){
        val prefs: SharedPreferences = context.getSharedPreferences(prefVariable, PRIVATE_MODE)
        with (prefs.edit()) {
            putBoolean(prefVariable, state)
            apply()
            commit()
        }
    }
}