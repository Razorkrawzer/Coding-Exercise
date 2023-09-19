package com.george.codingexercise.domain.usecase

import com.george.codingexercise.domain.Repository
import javax.inject.Inject

class GetItems @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke() = repository.getItems()
}