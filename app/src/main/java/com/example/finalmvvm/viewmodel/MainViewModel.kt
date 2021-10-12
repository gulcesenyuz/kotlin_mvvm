package com.example.getamovie.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.getamovie.model.MovieModel
import com.example.getamovie.model.MovieSearchResponse
import com.example.getamovie.repository.MainRepository
import kotlinx.coroutines.*
import org.json.JSONException
import retrofit2.Call
import retrofit2.Response

class MainViewModel constructor(private val mainRepository: MainRepository,
                                val movies: MutableLiveData<List<MovieModel>>
):ViewModel() {
    val errorMessage=MutableLiveData<String>()
    var job:Job?=null

    val loading = MutableLiveData<Boolean>()

    fun getMoviesBySearch(){
        println("getMoviesBySearch")


        job = CoroutineScope(Dispatchers.IO ).launch{
            println("CoroutineScope")
            val response=mainRepository.getMovie()

            response.enqueue(object: retrofit2.Callback<MovieSearchResponse>{
                override fun onResponse(
                        call: Call<MovieSearchResponse>,
                        response: Response<MovieSearchResponse>
                ) {

                    if (response.code() == 200) {
                        Log.d("Tag", "the response " + response.body().toString())
                        if (response.body() != null) {
                            movies.postValue(response.body()!!.getMovies())
                            //movies.value = response.body()!!.getMovies()
                        }
                    } else {
                        try {
                            println(response.code())
                            println("**"+response.errorBody()!!.string())
                        }catch (e: JSONException){
                            println("!!!!!!!!!!")
                            e.printStackTrace()
                        }
                    }
                }

                override fun onFailure(call: Call<MovieSearchResponse>, t: Throwable) {
                    t.printStackTrace()
                }
            })

        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}