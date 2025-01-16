package com.example.shreebhagvatgeeta.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.shreebhagvatgeeta.model.ChapterResponseItem
import com.example.shreebhagvatgeeta.model.verse.VerseResponse
import com.example.shreebhagvatgeeta.respository.AppRespository
import kotlinx.coroutines.flow.Flow

class MainViewModel():ViewModel() {
    val appRespository=AppRespository()

    fun getAllChapters() : Flow<List<ChapterResponseItem>> = appRespository.getAllChapter()

    fun allVerses(chapterNumber:Int) : Flow<List<VerseResponse>> = appRespository.allVerses(chapterNumber)

    fun versesDetails(chapterNo:Int,verseNo:Int):Flow<VerseResponse> = appRespository.versesDetails(chapterNo,verseNo)

}