package com.omaradev.cocktail.presentation.main

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.omaradev.cocktail.R
import com.omaradev.cocktail.data.local.CocktailDao
import com.omaradev.cocktail.data.local.CocktailDaoImpl
import com.omaradev.cocktail.data.repository.CocktailRepositoryImpl
import com.omaradev.cocktail.domain.model.Game
import com.omaradev.cocktail.domain.model.Question
import com.omaradev.cocktail.presentation.main.viewmodel.MainViewModel
import com.omaradev.cocktail.presentation.main.viewmodel.MainViewModelFactory
import com.omaradev.cocktail.presentation.ui.theme.CocktailTheme

class MainActivity : ComponentActivity() {
    private val questions = listOf(
        Question(question = "Question One ?", correctQuestion = "B1", inCorrectQuestion =  "A1"),
        Question(question = "Question Two ?", correctQuestion = "B2", inCorrectQuestion =  "A2"),
        Question(question = "Question Three ?", correctQuestion = "B3", inCorrectQuestion =  "A3"),
        Question(question = "Question Four ?", correctQuestion = "B4", inCorrectQuestion =  "A4"),
        Question(question = "Question Five ", correctQuestion = "B5", inCorrectQuestion =  "A5?"),
        Question(question = "Question Six ?", correctQuestion = "B6", inCorrectQuestion =  "A6"),
        Question(question = "Question Seven ?", correctQuestion = "B7", inCorrectQuestion =  "A7"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CocktailTheme {
                val sharedPreferences =
                    LocalContext.current.getSharedPreferences("Game", Context.MODE_PRIVATE)
                val dao: CocktailDao = CocktailDaoImpl()

                val viewModel: MainViewModel by viewModels {
                    MainViewModelFactory(CocktailRepositoryImpl(sharedPreferences, dao))
                }

                saveQuestionsToLocalDB(viewModel)

                viewModel.getAllQuestions()?.let {
                    val questions by it.observeAsState(initial = emptyList())
                    Game(questions = questions).let { game ->
                        viewModel.setGame(game = game)
                    }
                }

                QuestionsScreen(viewModel)
            }
        }
    }

    private fun saveQuestionsToLocalDB(viewModel: MainViewModel) {
        questions.forEach { question ->
            viewModel.saveQuestion(question)
        }
    }
}

@Composable
fun QuestionsScreen(viewModel: MainViewModel) {
    var gameState by remember { mutableStateOf(GameState(viewModel.getFirstQuestion())) }
    gameState.highScore = viewModel.getHighScore().toString()
    val context = LocalContext.current

    Column(Modifier.background(Color.White)) {
        Text(
            text = stringResource(id = R.string.game_title),
            Modifier
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally),
            style = baseTextStyle.copy(fontSize = 32.sp)
        )

        Text(
            text = stringResource(id = R.string.high_score) + " " + gameState.highScore,
            Modifier
                .padding(top = 8.dp)
                .align(Alignment.CenterHorizontally),
            style = baseTextStyle
        )

        Text(
            text = stringResource(id = R.string.current_score) + " " + gameState.currentScore,
            Modifier
                .padding(top = 8.dp)
                .align(Alignment.CenterHorizontally),
            style = baseTextStyle
        )

        Text(
            text = gameState.question.question,
            Modifier
                .padding(top = 32.dp)
                .align(Alignment.CenterHorizontally),
            style = baseTextStyle.copy(fontSize = 32.sp)
        )

        AnswerCard(gameState.question.correctQuestion, gameState.answerOneIsSelected) {
            gameState = gameState.copy(
                answerOneIsSelected = true,
                answerTwoIsSelected = false,
                answer = gameState.question.correctQuestion
            )
        }

        AnswerCard(gameState.question.inCorrectQuestion, gameState.answerTwoIsSelected) {
            gameState = gameState.copy(
                answerOneIsSelected = false,
                answerTwoIsSelected = true,
                answer = gameState.question.inCorrectQuestion
            )
        }

        Button(
            onClick = {
                if (viewModel.isLastIndex().not()) {
                    if (gameState.answer.isBlank()) {
                        Toast.makeText(context, "Please Select Answer", Toast.LENGTH_SHORT).show()
                    } else {
                        viewModel.answer(gameState.question, gameState.answer)

                        viewModel.nextQuestion()

                        viewModel.questionLiveData.value?.let {
                            gameState = gameState.copy(question = it)
                        }

                        gameState = gameState.copy(
                            currentScore = viewModel.scoreLiveData.value?.currentScore.toString(),
                            highScore = viewModel.scoreLiveData.value?.highScore.toString(),
                            answerOneIsSelected = false,
                            answerTwoIsSelected = false,
                            answer = ""
                        )
                    }
                }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 16.dp, horizontal = 32.dp)
        ) {
            Text(
                text = stringResource(id = R.string.confirm),
                color = Color.White,
                style = TextStyle(fontSize = 24.sp),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

data class GameState(
    val question: Question,
    var highScore: String = "0",
    val currentScore: String = "0",
    val answer: String = "",
    val answerOneIsSelected: Boolean = false,
    val answerTwoIsSelected: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnswerCard(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(if (isSelected) Color.Black else Color.Gray),
        modifier = Modifier
            .padding(top = 8.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        onClick = onClick
    ) {
        Text(
            text = text,
            Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally),
            style = TextStyle(
                color = Color.White,
                fontSize = 32.sp,
                fontFamily = FontFamily(Font(R.font.cairoregular))
            )
        )
    }
}

private val baseTextStyle = TextStyle(
    color = Color.Black,
    fontSize = 16.sp,
    fontFamily = FontFamily(Font(R.font.cairobold))
)
