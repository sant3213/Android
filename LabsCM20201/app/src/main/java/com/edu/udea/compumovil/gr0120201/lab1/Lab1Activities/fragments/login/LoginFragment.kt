package com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.ViewModel.UserViewModel
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.models.User
import com.edu.udea.compumovil.gr0120201.lab1.R
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*


class LoginFragment: Fragment() {

    private lateinit var mUserViewModel: UserViewModel

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
            getUser()
        }
        // Inflate the layout for this fragment
        return view
    }

    private fun getUser(){
        val userName = username.text.toString()
        //val password = addPassword.text.toString()
    println("voy a buscar"+userName)
            // Add Data to Database

        // UserViewModelu
        var user = User()
        user = mUserViewModel.getUser(userName)
        println("usuario-->"+user)
        /*
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            println("usuarios"+user);
        })*/




    }
}
