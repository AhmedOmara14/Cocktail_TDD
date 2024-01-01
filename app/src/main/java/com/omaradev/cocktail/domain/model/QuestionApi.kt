package com.omaradev.cocktail.domain.model

data class QuestionApi(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)