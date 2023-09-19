package com.george.codingexercise.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.george.codingexercise.R
import com.george.codingexercise.domain.model.ItemsModel

class MainAdapter(private var itemsList: List<ItemsModel> = emptyList()) :
    RecyclerView.Adapter<MainViewHolder>() {


    fun updateList(list: List<ItemsModel>) {
        itemsList = list
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        )
    }

    override fun getItemCount() = itemsList.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.render(itemsList[position])
    }
}