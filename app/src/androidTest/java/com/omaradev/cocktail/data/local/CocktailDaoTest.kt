package com.omaradev.cocktail.data.local

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.content.Context
import android.util.Log
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.omaradev.cocktail.domain.model.Question
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.argThat
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify


@RunWith(MockitoJUnitRunner::class)
class CocktailDaoTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var cocktailDao: CocktailDao
    private lateinit var cocktailDatabase: CocktailDatabase

    @Captor
    private lateinit var captor: ArgumentCaptor<List<Question>> // Change to List<Question>

    @Before
    fun setUp() {
        /**
         * in Memory DB, use it because two options
         *  1 - Speed : store data in ram not on disk
         *  2 - isolation : Created and destroyed in memory
         **/
        val context = ApplicationProvider.getApplicationContext<Context>()
        cocktailDatabase = Room.inMemoryDatabaseBuilder(
            context, CocktailDatabase::class.java
        ).allowMainThreadQueries().build()

        cocktailDao = cocktailDatabase.cocktailDao()

        captor = ArgumentCaptor.forClass(List::class.java as Class<List<Question>>)
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

    @Test
    fun `testSaveQuestion_UsingArgumentCaptor`() {
        val mockQuestion = Question(
            question = "Q",
            correctQuestion = "A",
            inCorrectQuestion = "B"
        )

        cocktailDao.saveQuestion(mockQuestion)

        val mockObserver = mock<Observer<List<Question>>>()

        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            cocktailDao.getAllQuestions().observeForever(mockObserver)
        }

        Thread.sleep(1000)

        val captor = argumentCaptor<List<Question>>()
        verify(mockObserver).onChanged(captor.capture())

        // Retrieve the captured argument
        val capturedArgument = captor.firstValue

        // Assert that the captured list is not null and contains the mock question
        assertNotNull(capturedArgument)
        assertTrue(capturedArgument.isNotEmpty())
    }

    @Test
    fun `testSaveQuestion_UsingValidationOnArgument`() {
        val mockQuestion = Question(
            question = "Q",
            correctQuestion = "A",
            inCorrectQuestion = "B"
        )

        cocktailDao.saveQuestion(mockQuestion)

        val mockObserver = mock<Observer<List<Question>>>()

        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            cocktailDao.getAllQuestions().observeForever(mockObserver)
        }

        Thread.sleep(1000)

        verify(mockObserver).onChanged(argThat { questions ->
            questions[0].question == mockQuestion.question
        })
    }

    @Test
    fun `testFindQuestionById`() {
        val q1 = Question(1, "a1", "b1", "Q1")
        val q2 = Question(2, "a2", "b2", "Q2")

        cocktailDao.saveQuestion(q1)
        cocktailDao.saveQuestion(q2)

        val mockObserver = mock<Observer<Question>>()

        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            cocktailDao.getQuestionById(q1.id).observeForever(mockObserver)
        }

        verify(mockObserver).onChanged(argThat { question ->
            question.question == q1.question
        })
    }
}
