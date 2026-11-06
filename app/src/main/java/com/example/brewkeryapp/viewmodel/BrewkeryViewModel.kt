package com.example.brewkeryapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brewkeryapp.data.model.Category
import com.example.brewkeryapp.data.model.MenuItem
import com.example.brewkeryapp.data.model.Meta
import com.example.brewkeryapp.data.repository.BrewkeryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BrewkeryViewModel : ViewModel() {

    private val repository = BrewkeryRepository()

    private val _items = MutableStateFlow<List<MenuItem>>(emptyList())
    val items: StateFlow<List<MenuItem>> = _items.asStateFlow()

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories.asStateFlow()

    private val _meta = MutableStateFlow<Meta?>(null)
    val meta: StateFlow<Meta?> = _meta.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadMenu()
    }

    fun loadMenu() {
        viewModelScope.launch {

            _isLoading.value = true
            _error.value = null

            try {
                val response = repository.getMenu()

                _items.value = response.items
                _categories.value = response.categories
                _meta.value = response.meta

            } catch (e: Exception) {
                _error.value = e.message ?: "Something went wrong"
            }

            _isLoading.value = false
        }
    }
}