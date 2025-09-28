package com.example.informatikamobile.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
// Corrected imports
import com.example.informatikamobile.data.model.BookDoc
import com.example.informatikamobile.data.network.RetrofitInstance
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _books = MutableLiveData<List<BookDoc>>(emptyList())
    val books: LiveData<List<BookDoc>> = _books

    fun fetchBooks(query: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.searchBooks(query, 10)
                if (response.isSuccessful) {
                    val result = response.body()?.docs ?: emptyList()
                    _books.postValue(result) // Correctly uses postValue for background thread safety
                    Log.d("SUCCESS_GET_DATA", result.toString())
                } else {
                    Log.e("API_ERROR", "${response.code()} ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("API_EXCEPTION", e.localizedMessage ?: "Unknown error")
            }
        }
    }
}