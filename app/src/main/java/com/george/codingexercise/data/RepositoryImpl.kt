package com.george.codingexercise.data

import android.util.Log
import com.george.codingexercise.data.network.FetchApiService
import com.george.codingexercise.data.network.response.toDomain
import com.george.codingexercise.domain.Repository
import com.george.codingexercise.domain.model.ItemsModel
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: FetchApiService) : Repository {
    override suspend fun getItems(): List<ItemsModel> {
        runCatching { apiService.getItems() }
            .onSuccess { items -> return items.map { it.toDomain() } }
            .onFailure { Log.i("", "An error occurred ${it.message}") }

        return emptyList()
    }
}