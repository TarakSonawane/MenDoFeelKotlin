package com.example.mendofeelkotlin.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mendofeelkotlin.databinding.RecyclerviewItemBinding
import com.neovisionaries.i18n.CountryCode

class RecyclerAdapterCountry(val list: List<String> , private val cellClickListener: CellClickListener) : RecyclerView.Adapter<RecyclerAdapterCountry.ViewHolder>() {

    private lateinit var binding : RecyclerviewItemBinding
    interface CellClickListener {
        fun getcountry(data : String)
    }
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        binding = RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = list[position]

        val countryName = CountryCode.getByCode(ItemsViewModel.uppercase()).getName()

        Log.d("TAG", "onBindViewHolder: "+countryName)

        // sets the text to the textview from our itemHolder class

       binding.objectName.text = countryName.toString()

        holder.itemView.setOnClickListener {
            cellClickListener.getcountry(ItemsViewModel)
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