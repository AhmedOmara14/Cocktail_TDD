package com.omaradev.cocktail.data.repository

import android.content.SharedPreferences
import com.omaradev.cocktail.domain.repository.CocktailRepository

class CocktailRepositoryImpl(sharedPreferences: SharedPreferences) : CocktailRepository {
    override fun saveScore(score: Int) {
        TODO("Not yet implemented")
    }
}