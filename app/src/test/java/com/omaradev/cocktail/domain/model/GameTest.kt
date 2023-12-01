package com.omaradev.cocktail.domain.model

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class GameTest {
    @Mock
    private lateinit var question: Question

    @Mock
    private lateinit var score: Score

    @Test
    fun test_whenAnswerQuestionCorrectly_incrementScore() {
        val game = Game(listOf(question),score)
        question.correctQuestion = "CorrectAnswer"

        game.answer(question,"CorrectAnswer")

        verify(question).answeredQuestion = "CorrectAnswer"
    }
}