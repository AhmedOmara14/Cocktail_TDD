package com.omaradev.cocktail.domain.model 
 
class Game(var questions: List<Question>, var score: Score = Score()) { 
    private var questionIndex = 0 
    fun answer( 
        question: Question, 
        option: String, 
        highScore: Int? = null, 
        handleScore: (() -> Unit)?= null, 
    ) { 
        question.answeredQuestion = option 
        if (question.answer()) { 
            score.highScore = highScore ?: 0 
            score.incrementScore() 
            handleScore?.invoke() 
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