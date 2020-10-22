package com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.utils

import android.content.Context
import android.content.SharedPreferences
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.fragments.login.LoginFragment


class Prefs(context: Context) {
    val SHARED_STATE = "userState"
    private var PRIVATE_MODE = 0
    var isLoggued= false

    fun getUserState(context: Context):Boolean{
        val prefs: SharedPreferences = context.getSharedPreferences(SHARED_STATE, PRIVATE_MODE)
        isLoggued= prefs.getBoolean(LoginFragment.SHARED_PREFS, false)
      return isLoggued
    }

    fun setUserState(context: Context, isLogguedIn: Boolean){
        val prefs: SharedPreferences = context.getSharedPreferences(SHARED_STATE, PRIVATE_MODE)
        with (prefs.edit()) {
            putBoolean(SHARED_STATE, isLogguedIn)
            apply()
            commit()
        }
    }
}