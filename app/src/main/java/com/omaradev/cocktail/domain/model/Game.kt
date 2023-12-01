package com.omaradev.cocktail.domain.model

class Game(private var questions: List<Question>, var score: Score = Score()) {
    private var questionIndex = 0
    fun answer(question: Question, option: String) {
        question.answeredQuestion = option
        if (question.answer()) {
            score.incrementScore()
        }
    }

    fun isLastIndex(): Boolean = questionIndex == questions.size - 1

    fun nextQuestion(): Question? {
        if (questionIndex + 1 < questions.size) {
            questionIndex++
            return questions[questionIndex]
        }
        return null
    }

    fun getFirstQuestion() = questions[0]

}