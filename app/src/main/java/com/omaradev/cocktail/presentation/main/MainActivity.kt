package com.omaradev.cocktail.presentation.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.omaradev.cocktail.domain.model.Game
import com.omaradev.cocktail.domain.model.Question
import com.omaradev.cocktail.presentation.ui.theme.CocktailTheme

class MainActivity : ComponentActivity() {
    private val questions = listOf(
        Question("A1", "B1", "Question One ?"),
        Question("A2", "B2", "Question Two ?"),
        Question("A3", "B3", "Question Three ?"),
        Question("A4", "B4", "Question Four ?"),
        Question("A5", "B5", "Question Five ?"),
        Question("A6", "B6", "Question Six ?"),
        Question("A7", "B7", "Question Seven ?"),
    )
    private val game = Game(questions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CocktailTheme {
                QuestionsScreen(game)
            }
        }
    }
}

@Composable
fun QuestionsScreen(game: Game) {
    var gameState by remember { mutableStateOf(GameState(game.getFirstQuestion())) }
    var context = LocalContext.current

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
                if (game.isLastIndex().not()) {
                    if (gameState.answer.isBlank()) {
                        Toast.makeText(context, "Please Select Answer", Toast.LENGTH_SHORT).show()
                    } else {
                        game.answer(gameState.question, gameState.answer)

                        game.nextQuestion()?.let {
                            gameState = gameState.copy(question = it)
                        }

                        gameState = gameState.copy(
                            currentScore = game.score.currentScore.toString(),
                            highScore = game.score.highScore.toString(),
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
    val highScore: String = "0",
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
