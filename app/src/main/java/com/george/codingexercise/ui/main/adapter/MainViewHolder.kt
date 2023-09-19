package com.george.codingexercise.ui.main.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.george.codingexercise.databinding.ItemMainBinding
import com.george.codingexercise.domain.model.ItemsModel

class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemMainBinding.bind(view)

    fun render(itemsResponse: ItemsModel) {

        binding.tvId.text = "Id: ${itemsResponse.id}"
        binding.tvListId.text = "List Id: ${itemsResponse.listId}"
        binding.tvName.text = "Name: ${itemsResponse.name}"
    }
}