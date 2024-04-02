package com.example.digital

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface{
    @GET("photos")
    fun getData() : Call<List<DataClass>>
}
class ApiClient {
    companion object{
        // Create a Retrofit instance with base URL and Gson converter factory
        var retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")//// Base URL for API endpoint
            .addConverterFactory(GsonConverterFactory.create())//// Gson converter for JSON parsing
            .build()
            .create(ApiInterface::class.java) // Create implementation of ApiInterface
    }
}