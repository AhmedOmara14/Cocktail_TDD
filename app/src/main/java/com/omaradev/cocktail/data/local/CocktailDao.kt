package com.omaradev.cocktail.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.omaradev.cocktail.domain.model.Question

@Dao
interface CocktailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveQuestion(question: Question)

    @Query("SELECT * FROM Question")
    fun getAllQuestions(): LiveData<List<Question>>

    @Query("SELECT * FROM Question WHERE id = :id")
    fun getQuestionById(id: Int): LiveData<Question>
}

open class CocktailDaoImpl(private val cocktailDao: CocktailDao? = null) : CocktailDao {
    private val questions: MutableLiveData<List<Question>> = MutableLiveData(emptyList())
    private val questionsList: ArrayList<Question> = arrayListOf()

    override fun saveQuestion(question: Question) {
        questionsList.add(question)
        questions.value = (questionsList)

        cocktailDao?.saveQuestion(question)
    }

    override fun getAllQuestions(): LiveData<List<Question>> = questions
    override fun getQuestionById(id: Int): LiveData<Question> {
        TODO("Not yet implemented")
    }

}