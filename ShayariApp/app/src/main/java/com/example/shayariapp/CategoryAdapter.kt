package com.example.shayariapp

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shayariapp.databinding.ItemCategoryBinding

class CategoryAdapter(val mainActivity: MainActivity, val list: ArrayList<CategoryModel>) : RecyclerView.Adapter<CategoryAdapter.CatViewHolder>() {

    val colorList = arrayListOf<String>("#f1c40f","#e67e22","#3498db","#2ecc71","#9b59b6")

    class CatViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        return CatViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.binding.itemText.text = list[position].name.toString()

        holder.binding.itemText.setBackgroundColor(Color.parseColor(colorList[position % 5]))


        holder.binding.root.setOnClickListener {
            val intent = Intent(mainActivity,AllShayariActivity::class.java)

            intent.putExtra("ID",list[position].id)
            intent.putExtra("name",list[position].name)

            mainActivity.startActivity(intent)
        }
    }
}