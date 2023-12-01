package com.omaradev.cocktail.domain.model

class Score {
    var currentScore = 0
    var highScore = 0

    fun incrementScore() {
        currentScore++
        if (currentScore > highScore)
            highScore = currentScore
    }
}