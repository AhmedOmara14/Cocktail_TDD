package com.omaradev.cocktail.data.local

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.omaradev.cocktail.domain.model.Question
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
@RunWith(MockitoJUnitRunner::class)
class CocktailDaoTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var cocktailDao: CocktailDao
    private lateinit var cocktailDatabase: CocktailDatabase

    @Before
    fun setUp() {
        /**
         * in Memory DB, use it because two options
         *  1 - Speed : store data in ram not on disk
         *  2 - isolation : Created and destroyed in memory
         **/
        cocktailDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), CocktailDatabase::class.java
        ).build()

        cocktailDao = cocktailDatabase.cocktailDao()
    }

    @After
    fun finish() {
        cocktailDatabase.close()
    }

    @Test
    fun `testGetAllQuestions_ReturnEmptyList`() {
        val mockObserver = mock<Observer<List<Question>>>()

        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            cocktailDao.getAllQuestions().observeForever(mockObserver)
        }

        // delay (1000 milliseconds) to allow onChanged to be called.
        Thread.sleep(1000)

        verify(mockObserver).onChanged(emptyList())
    }
}