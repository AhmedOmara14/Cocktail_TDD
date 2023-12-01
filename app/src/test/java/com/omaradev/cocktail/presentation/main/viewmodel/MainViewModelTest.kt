package com.omaradev.cocktail.presentation.main.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.omaradev.cocktail.domain.model.Question
import com.omaradev.cocktail.domain.model.Score
import com.omaradev.cocktail.domain.repository.CocktailRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.eq
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    //used taskExecutorRule for test LiveData and ViewModel
    //because LiveData and ViewModel work on main thread
    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var repository: CocktailRepository
    @Mock
    private lateinit var viewModel: MainViewModel
    @Mock
    private lateinit var loadingObserver: Observer<Boolean>
    @Mock
    private lateinit var errorObserver: Observer<Boolean>
    @Mock
    private lateinit var questionObserver: Observer<Question>
    @Mock
    private lateinit var scoreObserver: Observer<Score>

    @Before
    fun setUp() {
        viewModel = MainViewModel(repository)
        viewModel.getLoading().observeForever(loadingObserver)
        viewModel.getError().observeForever(errorObserver)
        viewModel.getQuestion().observeForever(questionObserver)
        viewModel.getScore().observeForever(scoreObserver)
    }

    @Test
    fun test_whenInitGame_showLoading(){
        viewModel.initGame()

        verify(loadingObserver).onChanged(eq(true))
    }

    @Test
    fun test_whenInitGame_hideError(){
        viewModel.initGame()

        verify(errorObserver).onChanged(eq(false))
    }

}