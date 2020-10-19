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
        val passwordIn = password.text.toString()
        mUserViewModel.getUser(userName)
        val imm = requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.getWindowToken(), 0);
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            println(user)
        })

        mUserViewModel.userGotted.observe(viewLifecycleOwner, Observer { user ->

            if (user == null || user.password != passwordIn) Toast.makeText(
                requireContext(),
                "Usuario o contraseña incorrecta",
                Toast.LENGTH_LONG
            ).show()
            else
                Toast.makeText(
                    requireContext(),
                    "Ingresando",
                    Toast.LENGTH_LONG
                ).show()// redireccionar al home (lista de lugares)
        })
    }
}
