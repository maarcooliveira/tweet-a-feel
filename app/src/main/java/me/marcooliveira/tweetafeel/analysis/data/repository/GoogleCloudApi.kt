package me.marcooliveira.tweetafeel.analysis.data.repository

import kotlinx.coroutines.Deferred
import me.marcooliveira.tweetafeel.analysis.data.model.SentimentRequestBody
import me.marcooliveira.tweetafeel.analysis.data.model.SentimentResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface GoogleCloudApi {
    @POST("v1/documents:analyzeSentiment")
    fun getSentiment(@Query("key") apiKey: String,
                     @Body body: SentimentRequestBody): Deferred<SentimentResponse>
}