package com.omaradev.cocktail.presentation.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omaradev.cocktail.data.repository.CocktailRepositoryImpl
import com.omaradev.cocktail.domain.model.Game
import com.omaradev.cocktail.domain.model.Question
import com.omaradev.cocktail.domain.model.Score

class MainViewModel() : ViewModel() {
    private val errorLiveData = MutableLiveData<Boolean>()
    val questionLiveData = MutableLiveData<Question>()
    val scoreLiveData = MutableLiveData<Score>()
    private lateinit var game: Game

    fun setGame(game: Game) {
        this.game = game
    }

    fun getError(): MutableLiveData<Boolean> = errorLiveData
    fun getQuestion(): MutableLiveData<Question> = questionLiveData
    fun getScore(): MutableLiveData<Score> = scoreLiveData

    fun initGame() {
        errorLiveData.value = false
    }

    fun nextQuestion() {
        questionLiveData.value = game.nextQuestion()
    }

    fun getFirstQuestion(): Question {
        questionLiveData.value = game.getFirstQuestion()
        return questionLiveData.value!!
    }

    fun answer(question: Question, answer: String) {
        game.answer(question, answer)
        scoreLiveData.value = game.score
    }

    fun isLastIndex() =
        game.isLastIndex()


}