package com.example.mendofeelkotlin.Adapters


import android.content.Context
import android.icu.number.NumberFormatter.with
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.example.mendofeelkotlin.ModelClass.Article
import com.example.mendofeelkotlin.R
import com.example.mendofeelkotlin.databinding.RecyclerviewnamesBinding
import com.squareup.picasso.Picasso


class RecyclerViewNews( val context: Context,val data1: ArrayList<Article>) : RecyclerView.Adapter<RecyclerViewNews.ViewHolder>() {

    private lateinit var binding : RecyclerviewnamesBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        binding = RecyclerviewnamesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {





        val ItemsViewModel = data1[position]

        binding.title.text = ItemsViewModel.title

        Log.d("fd", "onBindViewHolder: " + (ItemsViewModel.urlToImage))
        if(ItemsViewModel.urlToImage=="null") {
            binding.newsImage.setImageResource(R.drawable.ic_launcher_background)

        }
        else
        {
            Picasso.get().load(ItemsViewModel.urlToImage).into(binding.newsImage);

        }

        holder.itemView.setOnClickListener()
        {
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(context, Uri.parse(ItemsViewModel.url))

        }




    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return data1.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

    }
}