package com.george.codingexercise.data.network.response

import com.george.codingexercise.domain.model.ItemsModel
import com.google.gson.annotations.SerializedName

data class ItemsResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("listId") val listId: Int,
    @SerializedName("name") val name: String
)

fun ItemsResponse.toDomain(): ItemsModel {
    return ItemsModel(
        id = id,
        listId = listId,
        name = name
    )
}
