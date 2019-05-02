package me.marcooliveira.tweetafeel.analysis.data.repository

import kotlinx.coroutines.Deferred
import me.marcooliveira.tweetafeel.analysis.data.model.SentimentRequestBody
import me.marcooliveira.tweetafeel.analysis.data.model.SentimentResponse
import me.marcooliveira.tweetafeel.core.Service

class GoogleCloudService: Service<GoogleCloudApi> {

    fun getSentimentAsync(key: String, request: SentimentRequestBody): Deferred<SentimentResponse> {
        return retrofit("https://language.googleapis.com/", GoogleCloudApi::class.java)
            .getSentiment(key, request)
    }

}