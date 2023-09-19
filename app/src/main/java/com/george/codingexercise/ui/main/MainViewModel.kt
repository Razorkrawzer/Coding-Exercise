package com.george.codingexercise.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.george.codingexercise.domain.usecase.GetItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getItems: GetItems) : ViewModel() {

    private var _state = MutableStateFlow<MainState>(MainState.Loading)
    val state: StateFlow<MainState> = _state


    fun getList() {
        viewModelScope.launch {
            _state.value = MainState.Loading
            val result = withContext(Dispatchers.IO) { getItems.invoke() }
            if (result.isNotEmpty()) {

                val groupedByListId = result.groupBy { it.listId }
                val sortedGroupedItems = groupedByListId.toList()
                    .sortedWith(compareBy({ it.first }, { it.second.firstOrNull()?.name }))

                val filteredItems = sortedGroupedItems
                    .flatMap { it.second }
                    .filter { !it.name.isNullOrBlank() }

                _state.value = MainState.Success(filteredItems)

            } else {

                _state.value = MainState.Error("An error occurred")
            }
        }
    }
}