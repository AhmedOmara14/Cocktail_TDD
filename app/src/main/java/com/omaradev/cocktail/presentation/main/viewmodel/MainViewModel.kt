package com.omaradev.cocktail.presentation.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omaradev.cocktail.domain.model.Game
import com.omaradev.cocktail.domain.model.Question
import com.omaradev.cocktail.domain.model.Score
import com.omaradev.cocktail.domain.repository.CocktailRepository

class MainViewModel(val repository: CocktailRepository) : ViewModel() {
    private val errorLiveData = MutableLiveData<Boolean>()
    private val questionLiveData = MutableLiveData<Question>()
    private val scoreLiveData = MutableLiveData<Score>()

    fun getError(): MutableLiveData<Boolean> = errorLiveData
    fun getQuestion(): MutableLiveData<Question> = questionLiveData
    fun getScore(): MutableLiveData<Score> = scoreLiveData

    fun initGame() {
        errorLiveData.value = false
    }

    fun nextQuestion(game: Game) {
        questionLiveData.value = game.nextQuestion()
    }

    fun getFirstQuestion(game: Game): Question? {
        questionLiveData.value = game.getFirstQuestion()
        return questionLiveData.value
    }

}