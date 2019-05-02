package me.marcooliveira.tweetafeel.analysis.domain

import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import me.marcooliveira.tweetafeel.BaseUnitTest
import me.marcooliveira.tweetafeel.analysis.data.model.DocumentSentiment
import me.marcooliveira.tweetafeel.analysis.data.model.SentimentResponse
import me.marcooliveira.tweetafeel.analysis.data.repository.GoogleCloudService
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class GetAnalysisTest: BaseUnitTest() {

    private val service: GoogleCloudService = mock()

    @Test
    fun getSentimentAnalysis_runOnlyOnce() {
        val text = "This is positive and happy"
        runBlocking { GetAnalysis(service).execute(text) }

        verify(service, times(1)).getSentimentAsync(anyString(), any())
    }

    @Test
    fun getSentimentAnalysis_returnSentiment() {
        val text = "This is positive and happy"
        val response = SentimentResponse().apply {
            sentiment = DocumentSentiment(score = 1.0f)
        }
        whenever(service.getSentimentAsync(anyString(), any())).thenReturn(CompletableDeferred(response))
        val result = runBlocking { GetAnalysis(service).execute(text) }

        result.either({ error ->
            assertEquals(null, error)
        }, { value ->
            assertNotEquals(null, value)
        })
    }

    @Test
    fun getSentimentAnalysisWithError_returnError() {
        val text = "This is positive and happy"
        val response = SentimentResponse()

        whenever(service.getSentimentAsync(anyString(), any())).thenReturn(CompletableDeferred(response))
        val result = runBlocking { GetAnalysis(service).execute(text) }

        result.either({ error ->
            assertNotEquals(null, error)
        }, { value ->
            assertEquals(null, value)
        })
    }
}