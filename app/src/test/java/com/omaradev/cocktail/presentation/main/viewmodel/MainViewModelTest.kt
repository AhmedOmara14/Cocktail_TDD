package com.omaradev.cocktail.presentation.main.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.omaradev.cocktail.domain.model.Game
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
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    //used taskExecutorRule for test LiveData and ViewModel
    //because LiveData and ViewModel work on main thread
    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: CocktailRepository

    private lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var errorObserver: Observer<Boolean>

    @Mock
    private lateinit var questionObserver: Observer<Question>

    @Mock
    private lateinit var scoreObserver: Observer<Score>

    @Mock
    private lateinit var game: Game

    @Before
    fun setUp() {
        viewModel = MainViewModel(repository)
        viewModel.getError().observeForever(errorObserver)
        viewModel.getQuestion().observeForever(questionObserver)
        viewModel.getScore().observeForever(scoreObserver)

        viewModel.setGame(game)
    }

    @Test
    fun test_whenInitGame_hideError() {
        viewModel.initGame()

        verify(errorObserver).onChanged(eq(false))
    }

    @Test
    fun test_LoadingFirstQuestion() {
        val q1 = mock<Question>()

        whenever(game.getFirstQuestion())
            .thenReturn(q1)

        viewModel.getFirstQuestion()

        verify(questionObserver).onChanged(eq(q1))
    }

    @Test
    fun test_LoadingNextQuestion() {
        val q1 = mock<Question>()

        whenever(game.nextQuestion())
            .thenReturn(q1)

        viewModel.nextQuestion()

        verify(questionObserver).onChanged(eq(q1))
    }
}