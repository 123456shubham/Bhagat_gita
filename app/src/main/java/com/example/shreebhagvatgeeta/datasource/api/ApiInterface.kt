package com.example.shreebhagvatgeeta.datasource.api

import com.example.shreebhagvatgeeta.model.ChapterResponseItem
import com.example.shreebhagvatgeeta.model.verse.VerseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiInterface {
    @GET("v2/chapters/")
    fun getAllChapters():Call<List<ChapterResponseItem>>


    @GET("v2/chapters/{chapter}/verses/")
    fun getVerses(@Path("chapter") chapter:Int):Call<List<VerseResponse>>

    @GET("v2/chapters/{chapterNo}/verses/{versesNo}/")
    fun versesDetails(@Path("chapterNo") chapterNo:Int,@Path("versesNo") versesNo:Int):Call<VerseResponse>
}