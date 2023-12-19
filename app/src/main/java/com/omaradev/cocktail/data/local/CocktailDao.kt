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
    private val questionsList: ArrayList<Question> = arrayListOf() 
 
    override fun saveQuestion(question: Question) { 
        questionsList.add(question) 
        questions.value = (questionsList) 
    } 
 
    override fun getAllQuestions(): LiveData<List<Question>> = questions 
 
}  