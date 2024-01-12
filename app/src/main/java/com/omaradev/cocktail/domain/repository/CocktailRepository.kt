package com.omaradev.cocktail.domain.repository

import androidx.lifecycle.LiveData
import com.omaradev.cocktail.domain.model.Question
import com.omaradev.cocktail.domain.model.QuestionApi
import io.reactivex.Single

open interface CocktailRepository {
    fun saveScore(score: Int)
    fun getScore(): Int
    fun saveQuestion(question: Question)
    fun getAllQuestions(): LiveData<List<Question>>?

    fun getAllQuestionsFromApi(): Single<List<QuestionApi>>?
}