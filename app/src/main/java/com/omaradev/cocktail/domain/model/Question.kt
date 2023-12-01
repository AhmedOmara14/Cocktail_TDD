package com.omaradev.cocktail.domain.model

class Question(
    var correctQuestion: String,
    val inCorrectQuestion: String,
    val question: String
) {
    var answeredQuestion: String = ""
    fun answer(): Boolean {
        return answeredQuestion == correctQuestion
    }
}