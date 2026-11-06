package com.example.brewkeryapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brewkeryapp.data.model.MenuItem
import com.example.brewkeryapp.data.repository.BrewkeryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    private val repository = BrewkeryRepository()

    private val _item = MutableStateFlow<MenuItem?>(null)
    val item: StateFlow<MenuItem?> = _item.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun loadItem(id: Int) {

        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                _item.value = repository.getItemById(id)

            } catch (e: Exception) {
                _error.value = e.message ?: "Failed to load item"
            } finally {
                _isLoading.value = false
            }
        }
    }
}