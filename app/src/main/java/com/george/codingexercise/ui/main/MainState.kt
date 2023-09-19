package com.george.codingexercise.ui.main

import com.george.codingexercise.domain.model.ItemsModel

sealed class MainState {

    data object Loading : MainState()
    data class Error(val error: String) : MainState()
    data class Success(val itemsList: List<ItemsModel>) : MainState()
}