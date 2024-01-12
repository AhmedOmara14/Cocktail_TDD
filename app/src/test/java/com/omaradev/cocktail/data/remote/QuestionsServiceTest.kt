package com.omaradev.cocktail.data.remote

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.omaradev.cocktail.domain.model.QuestionApi
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuestionsServiceTest {
    private val userId = 1
    private val id = 1
    private val title = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit"
    private val body = "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum"

    private val testJson = """[{
    "userId": $userId,
    "id": $id,
    "title": "$title",
    "body": "$body"
}]"""


    /**
     * mockWebServer mock responses from network requests
     */
    @get:Rule
    val mockWebServer = MockWebServer()

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(mockWebServer.url("/"))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private val questionsService by lazy {
        retrofit.create(QuestionsService::class.java)
    }

    @Test
    fun test_getAllQuestions() {
        //use testJson to be a script a response
        //mockWebServer to enqueue a response

        mockWebServer.enqueue(
            MockResponse().setBody(testJson).setResponseCode(200)
        )

        val testObserver = questionsService.getAllQuestions().test()

        val response = listOf<QuestionApi>(QuestionApi(body, id, title, userId))
        testObserver.assertValues(response)
    }
}