package com.omaradev.cocktail.data.repository

import android.content.SharedPreferences
import com.omaradev.cocktail.domain.repository.CocktailRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
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
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    @Mock
    private lateinit var repository: CocktailRepository

    @Before
    fun setUp() {
        whenever(sharedPreferences.edit()).thenReturn(sharedPreferencesEditor)
        repository = CocktailRepositoryImpl(sharedPreferences)
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
}