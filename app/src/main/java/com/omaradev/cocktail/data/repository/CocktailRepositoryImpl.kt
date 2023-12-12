package com.omaradev.cocktail.data.repository

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import com.omaradev.cocktail.data.local.CocktailDao
import com.omaradev.cocktail.domain.model.Question
import com.omaradev.cocktail.domain.repository.CocktailRepository

class CocktailRepositoryImpl(
    private val sharedPreferences: SharedPreferences?,
    private val cocktailDao: CocktailDao?
) : CocktailRepository {
    private val highScoreKey = "HIGH_SCORE"
    override fun saveScore(score: Int) {
        val editor = sharedPreferences?.edit()
        editor?.putInt(highScoreKey, score)
        editor?.apply()
    }

    override fun getScore(): Int {
        val score = sharedPreferences?.getInt(highScoreKey, 0) ?: 0
        return score
    }

    override fun saveQuestion(question: Question) {
        cocktailDao?.saveQuestion(question)
    }

    override fun getAllQuestions(): LiveData<List<Question>>? =cocktailDao?.getAllQuestions()


}