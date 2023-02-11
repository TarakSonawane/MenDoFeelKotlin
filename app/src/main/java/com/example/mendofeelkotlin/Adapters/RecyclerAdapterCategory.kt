package com.example.mendofeelkotlin.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mendofeelkotlin.databinding.RecyclerviewItemBinding

class RecyclerAdapterCategory( val list: List<String> , private val cellClickListener: CellClickListener) : RecyclerView.Adapter<RecyclerAdapterCategory.ViewHolder>() {

    // create new views
    private lateinit var binding : RecyclerviewItemBinding

    interface CellClickListener {
        fun getcategory(data : String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        // that is used to hold list item
        binding = RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = list[position]

        // sets the image to the imageview from our itemHolder class

        // sets the text to the textview from our itemHolder class
        binding.objectName.text = ItemsViewModel

        holder.itemView.setOnClickListener {
            cellClickListener.getcategory(ItemsViewModel)
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return list.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

    }
}