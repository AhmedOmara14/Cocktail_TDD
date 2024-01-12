package com.omaradev.cocktail.data.remote

import com.omaradev.cocktail.domain.model.QuestionApi
import io.reactivex.Single
import retrofit2.http.GET

interface QuestionsService {
    @GET("questions.json")
    fun getAllQuestions(): Single<List<QuestionApi>>

}