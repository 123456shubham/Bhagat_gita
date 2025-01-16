package com.example.shreebhagvatgeeta.respository

import android.app.Application
import com.arramton.helping.restService.RetrofitBuilder
import com.example.shreebhagvatgeeta.model.ChapterResponseItem
import com.example.shreebhagvatgeeta.model.verse.VerseResponse
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppRespository {
    fun getAllChapter():Flow<List<ChapterResponseItem>> = callbackFlow{
        val callback = object : Callback<List<ChapterResponseItem>> {
            override fun onResponse(
                call: Call<List<ChapterResponseItem>>,
                response: Response<List<ChapterResponseItem>>
            ) {

                if (response.isSuccessful && response!=null){
                    trySend(response.body()!!)
                    close()
                }
            }

            override fun onFailure(call: Call<List<ChapterResponseItem>>, t: Throwable) {
                print("ExceptionVerse"+t.message)
                close(t)
            }
        }

        RetrofitBuilder.api.getAllChapters().enqueue(callback)
        awaitClose{}

    }

    fun allVerses(chapterNumber:Int):Flow<List<VerseResponse>> = callbackFlow{

        print("ExceptionVerse"+chapterNumber)

        val callback=object :Callback<List<VerseResponse>>{
            override fun onResponse(
                call: Call<List<VerseResponse>>,
                response: Response<List<VerseResponse>>
            ) {
                print("ExceptionVerse"+response)

                if (response.isSuccessful && response.body() !=null){
                    trySend(response.body()!!)
                    close()
                }
            }

            override fun onFailure(call: Call<List<VerseResponse>>, t: Throwable) {
                close(t)
                print("ExceptionVerse"+t.message)

            }

        }
        print("ExceptionVerse"+chapterNumber)

        RetrofitBuilder.api.getVerses(chapterNumber).enqueue(callback)
        awaitClose{}

    }

    fun versesDetails(chapterNumber: Int,versesNumber: Int):Flow<VerseResponse> = callbackFlow {
        val callback=object  : Callback<VerseResponse>{
            override fun onResponse(call: Call<VerseResponse>, response: Response<VerseResponse>) {
                if (response.isSuccessful && response!=null){
                    trySend(response.body()!!)
                    close()
                }
            }

            override fun onFailure(call: Call<VerseResponse>, t: Throwable) {
                print("ExceptionVerse"+t.message)
                close(t)
            }

        }
        RetrofitBuilder.api.versesDetails(chapterNumber,versesNumber).enqueue(callback)
        awaitClose{}
    }

}