package com.example.getamovie.request

import android.graphics.Movie
import com.example.getamovie.model.MovieModel
import com.example.getamovie.model.MovieSearchResponse
import com.example.getamovie.utils.Credentials
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("search/movie")
     fun getMovie(@Query("api_key") key:String,
                    @Query("query") query:String,
                    @Query("page") page:String
    ) : Call<MovieSearchResponse>

    companion object{
        var retrofitService:RetrofitService?=null
        fun getInstance():RetrofitService{
            if (retrofitService==null){
                val retrofit= Retrofit.Builder()
                    .baseUrl(Credentials.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)

            }
            return retrofitService!!
        }
    }

}