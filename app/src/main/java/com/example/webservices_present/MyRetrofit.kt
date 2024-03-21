package com.example.webservices_present

import android.util.Log
import android.widget.ProgressBar
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyRetrofit(private val progressBar: ProgressBar) {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/comments/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService: ApiService = retrofit.create(ApiService::class.java)

    suspend fun fetchData(urlString: String): List<Comment> {
        try {
            // Show progress bar
            progressBar.visibility = ProgressBar.VISIBLE

            // Make API request using Retrofit
            val response = apiService.fetchData(urlString)

            // Hide progress bar
            progressBar.visibility = ProgressBar.GONE

            return response
        } catch (e: Exception) {
            // Log error
            Log.e("Error", e.message ?: "Unknown error")
            // Return error message
            return emptyList()
        }
    }
}


