package com.omaradev.cocktail.domain.model

open class Question(
    var correctQuestion: String,
    val inCorrectQuestion: String,
    val question: String
) {
    var answeredQuestion: String = ""
    fun answer(): Boolean {
        return answeredQuestion == correctQuestion
    }
}