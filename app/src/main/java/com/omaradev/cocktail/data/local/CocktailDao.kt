package com.omaradev.cocktail.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.omaradev.cocktail.domain.model.Question

interface CocktailDao {
    fun saveQuestion(question: Question)

    fun getAllQuestions(): LiveData<List<Question>>
}

open class CocktailDaoImpl : CocktailDao {
    private val questions: MutableLiveData<List<Question>> = MutableLiveData(emptyList())
    override fun saveQuestion(question: Question) {
        questions.postValue(questions.value?.toMutableList()?.plus(question) ?: emptyList())
    }

    override fun getAllQuestions(): LiveData<List<Question>> = questions

}