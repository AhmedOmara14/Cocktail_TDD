package com.omaradev.cocktail.data.repository

import android.content.SharedPreferences
import com.omaradev.cocktail.data.remote.QuestionsService
import com.omaradev.cocktail.domain.model.QuestionApi
import com.omaradev.cocktail.domain.repository.CocktailRepository
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.inOrder
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class CocktailRepositoryImplTest {
    @Mock
    private lateinit var sharedPreferences: SharedPreferences

    @Mock
    private lateinit var questionsService: QuestionsService

    @Mock
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    private lateinit var repository: CocktailRepository

    @Before
    fun setUp() {
        whenever(sharedPreferences.edit()).thenReturn(sharedPreferencesEditor)
        repository = CocktailRepositoryImpl(sharedPreferences, null, questionsService)
    }

    @Test
    fun saveHighScore_ShouldSaveToSharedPreference() {
        val highScore = 100
        repository.saveScore(highScore)

        inOrder(sharedPreferencesEditor) {
            verify(sharedPreferencesEditor).putInt("HIGH_SCORE", highScore)
            verify(sharedPreferencesEditor).apply()
        }
    }

    @Test
    fun getHighScore_thatSavedFromSharedPreference() {
        val highScore = 100
        whenever(sharedPreferences.getInt("HIGH_SCORE", 0)).thenReturn(highScore)

        repository.getScore()

        verify(sharedPreferences).getInt(any(), any())
    }

    @Test
    fun test_getAllQuestionsWithEmitQuestions() {
        val question = QuestionApi("body", 1, "title", 1)

        whenever(questionsService.getAllQuestions()).thenReturn(
            Single.just(
                listOf<QuestionApi>(
                    question
                )
            )
        )

        val testObserver = repository.getAllQuestionsFromApi()?.test()

        testObserver?.assertValues(listOf(question))
    }

    @After
    fun tearsDown() {
        Mockito.clearAllCaches()
    }
}