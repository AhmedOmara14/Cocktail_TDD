package com.omaradev.cocktail.presentation.main.viewmodel

import androidx.lifecycle.Observer
import androidx.test.platform.app.InstrumentationRegistry
import com.omaradev.cocktail.data.local.CocktailDao
import com.omaradev.cocktail.data.local.CocktailDaoImpl
import com.omaradev.cocktail.data.repository.CocktailRepositoryImpl
import com.omaradev.cocktail.domain.model.Question
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.spy
import org.mockito.kotlin.verify

class MainViewModelTest {
    private var cocktailDao: CocktailDao = spy(CocktailDaoImpl())
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel(CocktailRepositoryImpl(sharedPreferences = null, cocktailDao))
    }

    @Test
    fun testSaveQuestion() {
        val question = mock<Question>()

        viewModel.saveQuestion(question)

        verify(cocktailDao).saveQuestion(any())
    }

    @Test
    fun testGetAllQuestions() {
        val mockQuestion = Question(
            question = "Q Name",
            correctQuestion = "Correct Ans",
            inCorrectQuestion = "InCorrect Ans",
        )

        cocktailDao.saveQuestion(mockQuestion)

        val mockObserver = mock<Observer<List<Question>>>()

        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            viewModel.getAllQuestions()?.observeForever(mockObserver)
        }

        verify(mockObserver).onChanged(listOf(mockQuestion))

    }
}