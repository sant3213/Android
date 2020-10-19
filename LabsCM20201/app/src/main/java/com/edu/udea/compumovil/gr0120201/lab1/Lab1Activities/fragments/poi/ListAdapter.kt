package com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.fragments.poi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edu.udea.compumovil.gr0120201.lab1.R
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.models.Poi
import kotlinx.android.synthetic.main.poi_row.view.*


class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var poiList = emptyList<Poi>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.poi_row, parent, false))
    }

    override fun getItemCount(): Int {
       return poiList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = poiList[position]
        holder.itemView.title_txt.text = currentItem.title
        holder.itemView.description_txt.text = currentItem.description
        holder.itemView.location_txt.text = currentItem.location
    }

    fun setData(poi: List<Poi>){
        this.poiList = poi
        notifyDataSetChanged()
    }
}