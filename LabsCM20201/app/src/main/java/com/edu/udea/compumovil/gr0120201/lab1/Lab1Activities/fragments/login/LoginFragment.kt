package com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.fragments.login

import android.app.Activity
import android.os.Bundle
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
import kotlinx.android.synthetic.main.fragment_login.view.*
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.models.User
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.utils.Prefs
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.*
import java.lang.IllegalStateException

class LoginFragment: Fragment() {



    private lateinit var mUserViewModel: UserViewModel
    var userr = User()

    companion object{

        lateinit var prefs: Prefs
    }
    val USER_STATE = "userState"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        prefs = Prefs(requireContext().applicationContext)
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

            startCoroutine(userName, passwordIn)
        }
        return view
    }

    fun startCoroutine(userName: String, passwordIn:String){
        val coroutineScope = CoroutineScope(Dispatchers.Main)

        val deferred1: Deferred<Unit> = coroutineScope.async {
            mUserViewModel.getUser(userName)
            clearData()
        }
        val deferred2: Deferred<Unit> = coroutineScope.async {
            mUserViewModel.userGotted.observe(viewLifecycleOwner, Observer { user ->
                try {
                    userr=user
                }catch (e:IllegalStateException){

                }
            })
        }
        val deferred3: Deferred<Unit> = coroutineScope.async {
            delay(100)
            validateUser(userr, passwordIn)
        }
        val deferred4: Deferred<Unit> = coroutineScope.async {
            delay(100)
            if (prefs.getPrefState(requireContext().applicationContext, USER_STATE)) {
                findNavController().navigate(R.id.action_loginFragment_to_poiListFragment)
              // activity?.supportFragmentManager?.beginTransaction()?.ba
            }else{
                Toast.makeText(
                    requireContext(),
                    "Usuario o contraseña incorrecta",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        coroutineScope.launch{
            deferred1.await()
            deferred2.await()
            deferred3.await()
            deferred4.await()
        }
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
            prefs.setPrefState(requireContext().applicationContext, USER_STATE,false)

        } else {
            Toast.makeText(
                requireContext(),
                "Ingresando",
                Toast.LENGTH_LONG
            ).show()
            prefs.setPrefState(requireContext().applicationContext, USER_STATE,true)
        }
    }
}