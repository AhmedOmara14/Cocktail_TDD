package com.omaradev.cocktail.presentation.main.viewmodel 
 
import androidx.lifecycle.LiveData 
import androidx.lifecycle.MutableLiveData 
import androidx.lifecycle.ViewModel 
import com.omaradev.cocktail.domain.model.Game 
import com.omaradev.cocktail.domain.model.Question 
import com.omaradev.cocktail.domain.model.Score 
import com.omaradev.cocktail.domain.repository.CocktailRepository 
 
open class MainViewModel(private val repository: CocktailRepository) : ViewModel() { 
    private lateinit var game: Game 
    private val errorLiveData = MutableLiveData<Boolean>() 
    val questionLiveData = MutableLiveData<Question>() 
    val scoreLiveData = MutableLiveData<Score>() 
 
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
        game.answer(question, answer, highScore = getHighScore()) { 
            scoreLiveData.value = game.score 
            saveHighScore(game.score.highScore) 
        } 
    } 
 
    fun isLastIndex() = 
        game.isLastIndex() 
 
    private fun saveHighScore(score: Int) { 
        if (scoreLiveData.value?.saveHighScore == true) 
            repository.saveScore(score) 
    } 
 
    fun getHighScore(): Int { 
        scoreLiveData.value?.let { 
            return it.highScore 
        } ?: kotlin.run { 
            scoreLiveData.value?.highScore = repository.getScore() 
            return repository.getScore() 
        } 
    } 
 
    fun getAllQuestions(): LiveData<List<Question>>? = repository.getAllQuestions() 
 
    fun saveQuestion(question: Question) { 
        repository.saveQuestion(question) 
    } 
}  