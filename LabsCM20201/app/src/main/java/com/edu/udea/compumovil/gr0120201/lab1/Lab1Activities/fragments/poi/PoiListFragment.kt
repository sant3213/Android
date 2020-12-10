package com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.fragments.poi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.ViewModel.PoiViewModel
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.fragments.login.LoginFragment
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.models.Poi
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.utils.Prefs
import com.edu.udea.compumovil.gr0120201.lab1.R
import kotlinx.android.synthetic.main.fragment_poi_list.view.*

class PoiListFragment : Fragment() {
    private lateinit var mPoiViewModel: PoiViewModel
    val USER_STATE = "userState"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_poi_list, container, false)
        val isLogguedIn = LoginFragment.prefs.getPrefState(requireContext().applicationContext, USER_STATE)
        if(!isLogguedIn){
            findNavController().navigate(R.id.loginFragment)
        }
        // Recyclerview
        val adapter = ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        mPoiViewModel = ViewModelProvider(this).get(PoiViewModel::class.java)
        mPoiViewModel.readAllData.observe(viewLifecycleOwner, Observer { poi ->
            adapter.setData(poi)
        })

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_poiListFragment_to_poiFragment)
        }

        return view
    }

}