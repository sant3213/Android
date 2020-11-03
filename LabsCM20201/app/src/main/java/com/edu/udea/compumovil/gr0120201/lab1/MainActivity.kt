package com.edu.udea.compumovil.gr0120201.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.utils.Prefs
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var prefs: Prefs
    }

    val USER_STATE = "userState"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        prefs = Prefs(applicationContext)
        //prefs.setPrefState(applicationContext,USER_STATE, false)
        setContentView(R.layout.activity_main)
        val appBarConfig= AppBarConfiguration.Builder(R.id.poiListFragment, R.id.poiListFragment)
            .build()
        setupActionBarWithNavController(
             findNavController(R.id.fragment),
            appBarConfig
        )
        if (prefs.getPrefState(applicationContext, USER_STATE)) {
            findNavController(R.id.fragment).navigate(R.id.action_loginFragment_to_poiListFragment)
        } else
            findNavController(R.id.fragment)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id = item.getItemId()

        //Configuracion
        if (id == R.id.action_two) {
            //findNavController(R.id.fragment)
            findNavController(R.id.fragment).navigate(R.id.settingsFragment)
            return true
        }
        //salir de la sesion
        if (id == R.id.action_three) {

            val view = layoutInflater.inflate(R.layout.popup_login_warning,null)

            val button = view.findViewById<Button>(R.id.button)
            findNavController(R.id.fragment).navigate(R.id.loginFragment)
            button.setOnClickListener {
                prefs.setPrefState(this, USER_STATE,  false)
            }

            val button2 = view.findViewById<Button>(R.id.button2)
            button2.setOnClickListener {
                exitProcess(1)
            }
          //  window.showAsDropDown(view)
            return true
        }

        return super.onOptionsItemSelected(item)

    }

    override fun onBackPressed() {
    }
}