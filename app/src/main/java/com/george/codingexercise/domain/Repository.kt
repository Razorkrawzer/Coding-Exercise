package com.george.codingexercise.domain

import com.george.codingexercise.domain.model.ItemsModel

interface Repository {

    suspend fun getItems(): List<ItemsModel>
}