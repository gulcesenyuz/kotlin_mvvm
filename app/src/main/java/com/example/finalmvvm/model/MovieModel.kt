package com.example.getamovie.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieModel(
    @SerializedName("results")
    @Expose
    val title: String,
    val poster_path: String,
    val release_date: String,
    val movie_id:Int,
    val vote_average:Float,
    val overview: String
    )