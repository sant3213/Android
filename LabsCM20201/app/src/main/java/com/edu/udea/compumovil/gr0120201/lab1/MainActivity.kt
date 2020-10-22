package com.edu.udea.compumovil.gr0120201.lab1


import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.utils.Prefs


class MainActivity : AppCompatActivity() {
    companion object{
        lateinit var prefs: Prefs
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        prefs = Prefs(applicationContext)

        setContentView(R.layout.activity_main)
        if (prefs.getUserState(applicationContext)) {
            findNavController(R.id.fragment).navigate(R.id.action_loginFragment_to_poiListFragment)
        }
        else
            findNavController(R.id.fragment)

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