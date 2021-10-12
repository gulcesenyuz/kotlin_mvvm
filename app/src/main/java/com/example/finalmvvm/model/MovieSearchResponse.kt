package com.example.getamovie.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieSearchResponse {
    @SerializedName("results")
    @Expose
    private val movies:List<MovieModel> = emptyList()
     fun getMovies():List<MovieModel>{
        return movies
    }
}