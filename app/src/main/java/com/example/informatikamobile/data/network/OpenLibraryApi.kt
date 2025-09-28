package com.example.informatikamobile.data.network

import com.example.informatikamobile.data.model.SearchResponse // Pastikan import SearchResponse benar
import retrofit2.Response // <-- PENTING: Pastikan ini adalah import yang benar
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenLibraryApi {
    @GET(value = "search.json")
    suspend fun searchBooks(
        @Query(value = "q") query: String,
        @Query(value = "limit") limit: Int
    ): Response<SearchResponse>
}