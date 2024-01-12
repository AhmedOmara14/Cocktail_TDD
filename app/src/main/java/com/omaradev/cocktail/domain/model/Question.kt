package com.omaradev.cocktail.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
open class Question(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var correctQuestion: String,
    val inCorrectQuestion: String,
    val question: String
) {
    var answeredQuestion: String = ""
    fun answer(): Boolean {
        return answeredQuestion == correctQuestion
    }
}