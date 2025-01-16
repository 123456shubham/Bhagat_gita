package com.example.shreebhagvatgeeta.model.verse

data class VerseResponse(
    val chapter_number: Int?,
    val commentaries: List<Commentary?>,
    val id: Int?,
    val slug: String?,
    val text: String?,
    val translations: List<Translation?>,
    val transliteration: String?,
    val verse_number: Int?,
    val word_meanings: String?
)