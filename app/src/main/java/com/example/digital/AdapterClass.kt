package com.example.digital

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
class AdapterClass(private var DataList: MutableList<DataClass>, private val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<AdapterClass.MyViewHolder>() {
    // Interface to handle item click events
    interface OnItemClickListener {
        fun onItemClick(data: DataClass)
    }

    // ViewHolder class to hold references to views
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.tittle)
        var image: ImageView = itemView.findViewById(R.id.image)//need to change id recycler setup
        // Bind data to views
        fun bind(data: DataClass) {
            title.text = data.title
            // Load image using Glide Library
            Glide.with(itemView.context)
                .load(data.thumbnailUrl)
                .into(image)
        }
    }
    // Create view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Inflate layout for each item view
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(itemView)
    }
    // Bind data to view holder
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = DataList[position]
        holder.bind(data)
        // Set click listener for each item view
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(data)
        }
    }
    // Return number of items in the data list
    override fun getItemCount(): Int {
        return DataList.size
    }
    // Function to update adapter data
    fun setData(newDataList: List<DataClass>) {
        DataList.clear() // Clear existing data
        DataList.addAll(newDataList) // Add new data
        notifyDataSetChanged() // Notify adapter about the changes
    }
}