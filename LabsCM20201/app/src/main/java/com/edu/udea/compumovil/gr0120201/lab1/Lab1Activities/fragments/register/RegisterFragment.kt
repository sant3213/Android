package com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.fragments.register

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.ViewModel.UserViewModel
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.models.User
import com.edu.udea.compumovil.gr0120201.lab1.R
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*

class RegisterFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.registerButton.setOnClickListener{
            insertDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase(){
        val userName = addUserName.text.toString()
        val email = addEmail.text.toString()
        val password = addPassword.text.toString()

        if(inputCheck(userName, email, password)){
            // Create User Object
            val user = User(userName, email, password)
            // Add Data to Database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Agregado exitosamente!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }else{
            Toast.makeText(requireContext(), "Por favor llene todos los campos.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(userName: String, email: String, lastName: String): Boolean{
        return !(TextUtils.isEmpty(userName) && TextUtils.isEmpty(email) && TextUtils.isEmpty(lastName))
    }
}