package com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.fragments.poi

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.edu.udea.compumovil.gr0120201.lab1.Lab1Activities.models.Poi
import com.edu.udea.compumovil.gr0120201.lab1.R
import kotlinx.android.synthetic.main.poi_row.view.*
import java.io.File


class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var poiList = emptyList<Poi>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

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
        holder.itemView.title_txt.text = currentItem.title
        //holder.itemView.description_txt.text = currentItem.description
        //holder.itemView.location_txt.text = currentItem.location
        //holder.itemView.imageView.setImageDrawable(ContextCompat.getDrawable())
        holder.itemView.rowLayout.setOnClickListener{
        val action = PoiListFragmentDirections.actionPoiListFragmentToPoiDetail(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun getImageList(){
        var gpath:String = Environment.getRootDirectory().absolutePath;
        print(gpath)
        var spath = "assets"
        val imgFile = File("/sdcard/Images/test_image.jpg")
        if(imgFile.exists()){

            var myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

    }

    fun setData(poi: List<Poi>){
        this.poiList = poi
        notifyDataSetChanged()
    }
}