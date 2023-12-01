package com.omaradev.cocktail.domain.model

import org.junit.Assert
import org.junit.Test
class ScoreTest {
    @Test
    fun test_whenIncrementScore_currentScoreIsIncremented() {
        val score = Score()

        score.incrementScore()

        Assert.assertEquals(1, score.currentScore)
    }

    @Test
    fun test_whenIncrementCurrentScoreAboveHighScore_highScoreIsIncremented() {
        val score = Score()
        score.currentScore = 10
        score.highScore = 5

        score.incrementScore()

        Assert.assertEquals(11, score.highScore)
    }

}