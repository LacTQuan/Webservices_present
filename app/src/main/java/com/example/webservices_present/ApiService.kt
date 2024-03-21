package com.example.webservices_present

import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun fetchData(@Url url: String): List<Comment>
}
