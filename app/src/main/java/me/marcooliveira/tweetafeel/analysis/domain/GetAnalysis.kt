package me.marcooliveira.tweetafeel.analysis.domain

import me.marcooliveira.tweetafeel.analysis.data.model.Document
import me.marcooliveira.tweetafeel.analysis.data.model.SentimentRequestBody
import me.marcooliveira.tweetafeel.analysis.data.repository.GoogleCloudService
import me.marcooliveira.tweetafeel.analysis.presentation.Sentiment
import me.marcooliveira.tweetafeel.core.Constants.GOOGLE_DOCUMENT_ENCODING
import me.marcooliveira.tweetafeel.core.Constants.GOOGLE_DOCUMENT_TYPE
import me.marcooliveira.tweetafeel.core.Constants.GOOGLE_KEY
import me.marcooliveira.tweetafeel.core.Either
import me.marcooliveira.tweetafeel.core.Error

class GetAnalysis(private val service: GoogleCloudService) {

    suspend fun execute(text: String): Either<Error, Sentiment> {
        return try {
            val requestBody = SentimentRequestBody().apply {
                document = Document(GOOGLE_DOCUMENT_TYPE, text)
                encoding = GOOGLE_DOCUMENT_ENCODING
            }
            val response = service.getSentimentAsync(GOOGLE_KEY, requestBody).await()
            Either.Value(SentimentMapper.map(response)!!)
        } catch (e: Exception) {
            Either.Error(Error(e.message))
        }
    }
}