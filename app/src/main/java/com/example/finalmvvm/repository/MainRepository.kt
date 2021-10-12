package com.example.getamovie.repository

import com.example.getamovie.request.RetrofitService
import com.example.getamovie.utils.Credentials

class MainRepository constructor(private val retrofitService: RetrofitService){
    suspend fun getMovie()=retrofitService.getMovie(Credentials.API_KEY, "Action", "1")

}