package com.edu.udea.compumovil.gr0120201.lab1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {
    companion object{

        const val SHARED_PREFS = "userState"

    }
    var isloggued=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        saveToShare()
        if (isloggued) findNavController(R.id.fragment).navigate(R.id.action_loginFragment_to_poiListFragment)
        else findNavController(R.id.fragment)
    }
    private fun saveToShare(){
        val sharedPrefSet = getPreferences(Context.MODE_PRIVATE)
        isloggued = sharedPrefSet.getBoolean(SHARED_PREFS,false)
        }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
/*
    override fun onBackPressed() {
        Toast.makeText(applicationContext, "Disabled Back Press", Toast.LENGTH_SHORT).show()
    }*/

}