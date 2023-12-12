package com.omaradev.cocktail.domain.repository

import androidx.lifecycle.LiveData
import com.omaradev.cocktail.domain.model.Question

interface CocktailRepository {
    fun saveScore(score: Int)
    fun getScore(): Int
    fun saveQuestion(question: Question)
    fun getAllQuestions(): LiveData<List<Question>>?
}