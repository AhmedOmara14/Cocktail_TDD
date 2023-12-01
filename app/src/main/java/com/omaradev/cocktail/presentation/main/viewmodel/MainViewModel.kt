package com.omaradev.cocktail.presentation.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omaradev.cocktail.domain.model.Question
import com.omaradev.cocktail.domain.model.Score
import com.omaradev.cocktail.domain.repository.CocktailRepository

class MainViewModel(repository: CocktailRepository) : ViewModel() {
    private val loadingLiveData = MutableLiveData<Boolean>()
    private val errorLiveData = MutableLiveData<Boolean>()
    private val questionLiveData = MutableLiveData<Question>()
    private val scoreLiveData = MutableLiveData<Score>()
    fun getLoading(): MutableLiveData<Boolean> = loadingLiveData
    fun getError(): MutableLiveData<Boolean> = errorLiveData
    fun getQuestion(): MutableLiveData<Question> = questionLiveData
    fun getScore(): MutableLiveData<Score> = scoreLiveData
    fun initGame() {
        TODO("Not yet implemented")
    }

}