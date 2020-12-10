package com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.fragments.poi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.models.Poi
import com.edu.udea.compumovil.gr0120201.lab1.R
import kotlinx.android.synthetic.main.poi_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var poiList = emptyList<Poi>()
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.poi_row,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return poiList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = poiList[position]
        var imageResource:Int
        val currentImageName = poiList[position].imageName

        when(currentImageName){
            "cove"->imageResource = R.drawable.cove
            "hobbiton"->imageResource = R.drawable.hobbiton
            "tongariro"->imageResource = R.drawable.tongariro
            "waiotapu"->imageResource = R.drawable.waiotapu
            else ->{ imageResource = R.drawable.cove}
        }
        holder.itemView.title_txt.text = currentItem.title
        if(currentItem.description.count()>24) {
            holder.itemView.description_in.text =
                currentItem.description.substring(0, 25).plus("...")
        }else{
            holder.itemView.description_in.text = currentItem.description
        }
        holder.itemView.imageView.setImageResource(imageResource)
        holder.itemView.rowLayout.setOnClickListener {
            val action = PoiListFragmentDirections.actionPoiListFragmentToPoiDetail(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

        fun setData(poi: List<Poi>) {
            this.poiList = poi
            notifyDataSetChanged()
        }
}