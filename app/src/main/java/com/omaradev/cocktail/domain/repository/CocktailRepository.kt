package com.omaradev.cocktail.domain.repository

interface CocktailRepository {
    fun saveScore(score: Int)
    fun getScore()
}