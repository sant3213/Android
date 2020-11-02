package com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.fragments.poi

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.ViewModel.PoiViewModel
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.models.Poi
import com.edu.udea.compumovil.gr0120201.lab1.R
import kotlinx.android.synthetic.main.fragment_poi.*
import kotlinx.android.synthetic.main.fragment_poi.view.*
import java.io.File

class PoiFragment : Fragment() {

    private lateinit var mPoiViewModel: PoiViewModel
    private var image:Uri? = null
    private val PICK_IMAGES_CODE = 0
    private lateinit var privateRootDir: File
    private lateinit var imagesDir: File

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_poi, container, false)

        mPoiViewModel = ViewModelProvider(this).get(PoiViewModel::class.java)

        view.add_btn.setOnClickListener{
            val imm = requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view?.getWindowToken(), 0)

           insertDataToDatabase()
            clearData()
        }

        view.pickImage.setOnClickListener{
            pickImage()
        }

        return view
    }

    private fun pickImage(){
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select image"), PICK_IMAGES_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == PICK_IMAGES_CODE){
            if(resultCode == Activity.RESULT_OK){
                val imageUri = data?.data

            }
        }
    }

    private fun clearData(){
        title_in.setText("")
        description_in.setText("")
        location_in.setText("")
    }


    private fun insertDataToDatabase(){
        var title =  title_in.text.toString()
        var description = description_in.text.toString()
        var location = location_in.text.toString()
        var imageName = "eta"


        if(inputCheck(title, description, location)){
            // Create Poi Object
            val poi = Poi(0, title, description, location, imageName)
            // Add Data to Database
            mPoiViewModel.addPoi(poi)
            Toast.makeText(requireContext(), "Agregado exitosamente!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_poiFragment_to_poiListFragment)
        }else{
            Toast.makeText(requireContext(), "Por favor llene todos los campos.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(title: String, description: String, location: String): Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description) && TextUtils.isEmpty(location))
    }

}