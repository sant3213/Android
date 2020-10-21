package com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.fragments.login

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.ViewModel.UserViewModel
import com.edu.udea.compumovil.gr0120201.lab1.R
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.models.User
import kotlinx.coroutines.*

class LoginFragment: Fragment() {

    private lateinit var mUserViewModel: UserViewModel
    var isloggued=false
    var userr = User()
    private var PRIVATE_MODE = 0

    companion object{

        const val SHARED_PREFS = "userState"

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_login, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.registerText.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }


        view.loginButton.setOnClickListener {
            val imm = requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view?.getWindowToken(), 0);

            val passwordIn = password.text.toString()
            val userName = username.text.toString()
            clearData()

            val coroutineScope = CoroutineScope(Dispatchers.Main)

            val deferred1: Deferred<Unit> = coroutineScope.async {
                mUserViewModel.getUser(userName)
                clearData()
            }
            val deferred2: Deferred<Unit> = coroutineScope.async {
                mUserViewModel.userGotted.observe(viewLifecycleOwner, Observer { user ->
                    userr=user
                })
                }
            val deferred3: Deferred<Unit> = coroutineScope.async {
                delay(100)
                validateUser(userr, passwordIn)
            }
            val deferred4: Deferred<Unit> = coroutineScope.async {
                delay(100)
                if (isloggued) findNavController().navigate(R.id.action_loginFragment_to_poiListFragment)
            }
            coroutineScope.launch{
                deferred1.await()
                deferred2.await()
                deferred3.await()
                deferred4.await()
            }
        }
        return view
    }


    private fun clearData(){
        username.setText("")
        password.setText("")
    }

    private fun validateUser(userr:User, pass:String){
        if (userr == null || userr.password != pass) {
            Toast.makeText(
                requireContext(),
                "Usuario o contraseña incorrecta",
                Toast.LENGTH_LONG
            ).show()
            saveToShare(false)
            getSharePref()

        } else {
            Toast.makeText(
                requireContext(),
                "Ingresando",
                Toast.LENGTH_LONG
            ).show()
            saveToShare(true)
            getSharePref()
        }
    }

    private fun saveToShare(isLoggued: Boolean){
        val sharedPrefSet = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPrefSet.edit()) {
            putBoolean(SHARED_PREFS, isLoggued)
            apply()
        }
    }

    private fun getSharePref() {
        val sharedPrefSet = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
            sharedPrefSet.getBoolean(SHARED_PREFS,false)
        isloggued= sharedPrefSet.getBoolean(SHARED_PREFS, false)
    }
}