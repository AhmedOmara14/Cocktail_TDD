package com.omaradev.cocktail.domain.model

import org.junit.Assert
import org.junit.Test

class QuestionTest {
    @Test
    fun test_WhenAddCorrectAnswer_returnTrue() {
        val question = Question("Correct", "InCorrect", "Q1")

        question.answeredQuestion = "Correct"

        Assert.assertTrue(question.answer())
    }

    @Test
    fun test_WhenAddInCorrectAnswer_returnFalse() {
        val question = Question("Correct", "InCorrect", "Q1")

        question.answeredQuestion = "Test"

        Assert.assertFalse(question.answer())
    }
}