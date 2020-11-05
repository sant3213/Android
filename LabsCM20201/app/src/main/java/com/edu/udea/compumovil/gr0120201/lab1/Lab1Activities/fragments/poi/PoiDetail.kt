package com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.fragments.poi

import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.ViewModel.PoiViewModel
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.models.Poi
import com.edu.udea.compumovil.gr0120201.lab1.R
import kotlinx.android.synthetic.main.fragment_poi_detail.*
import kotlinx.android.synthetic.main.fragment_poi_detail.view.*


class PoiDetail : Fragment() {
    private val args by navArgs<PoiDetailArgs>()
    private lateinit var mPoiViewModel: PoiViewModel
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        val view = inflater.inflate(R.layout.fragment_poi_detail, container, false)

        mPoiViewModel = ViewModelProvider(this).get(PoiViewModel::class.java)

        updateTextValues(args.currentPoi, view)
        view.image_view.setImageResource(getImageToShow(args.currentPoi.imageName))

        view.update_button.setOnClickListener {
            updatePoi()
        }

        view.delete_button.setOnClickListener{
            // Create Poi Object
            val poiToDelete = Poi(args.currentPoi.id, args.currentPoi.title, args.currentPoi.description, args.currentPoi.location, args.currentPoi.imageName)
            deletItem(poiToDelete)
        }

        return view
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateTextValues(currentPoi: Poi, view: View) {
        view.titleDetail_in.setText(currentPoi.title)
        view.descriptionDetail_in.setText(currentPoi.description)
        view.locationDetail_in.setText(currentPoi.location)
        view.image_name_edit.setText(currentPoi.imageName)
        view.descriptionDetail_in.justificationMode = JUSTIFICATION_MODE_INTER_WORD
    }

    private fun getImageToShow(imageName: String): Int {
        var imageResource:Int
        when(imageName){
            "cove"->imageResource = R.drawable.cove
            "hobbiton"->imageResource = R.drawable.hobbiton
            "tongariro"->imageResource = R.drawable.tongariro
            "waiotapu"->imageResource = R.drawable.waiotapu
            else ->{ imageResource = R.drawable.cove}
        }
        return imageResource
    }

    private fun updatePoi() {
        val title = titleDetail_in.text.toString()
        val description = descriptionDetail_in.text.toString()
        val location = locationDetail_in.text.toString()
        val imageName = image_name_edit.text.toString()

        if(inputCheck(title, description, location)){
            // Create Poi Object
            val updatedPoi = Poi(args.currentPoi.id, title, description, location, imageName)
            // Update Current Poi
            mPoiViewModel.updatePoi(updatedPoi)
            Toast.makeText(requireContext(), "Actualizado exitosamente!", Toast.LENGTH_SHORT).show()
            // Navigate Back
            findNavController().navigate(R.id.action_poiDetail_to_poiListFragment)
        }else{
            Toast.makeText(requireContext(), "Por favor complete todos los campos.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deletItem(poiToDelete:Poi){
        mPoiViewModel.deletePoi(poiToDelete)
        // Navigate Back
        findNavController().navigate(R.id.action_poiDetail_to_poiListFragment)
    }

    private fun inputCheck(title: String, description: String, location: String): Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description) && location.isEmpty())
    }

}