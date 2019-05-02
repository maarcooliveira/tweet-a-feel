package me.marcooliveira.tweetafeel.analysis.domain


import me.marcooliveira.tweetafeel.analysis.data.model.DocumentSentiment
import me.marcooliveira.tweetafeel.analysis.data.model.SentimentResponse
import me.marcooliveira.tweetafeel.analysis.presentation.Sentiment
import org.junit.Assert.assertEquals
import org.junit.Test

class SentimentMapperTest {

    @Test
    fun testNegativeRange() {
        val negativeMin = getSentimentWith(-1f)
        val negativeMax = getSentimentWith(-0.26f)

        assertEquals(Sentiment.NEGATIVE, SentimentMapper.map(negativeMin))
        assertEquals(Sentiment.NEGATIVE, SentimentMapper.map(negativeMax))
    }

    @Test
    fun testNeutralRange() {
        val neutralMin = getSentimentWith(-0.25f)
        val neutralMax = getSentimentWith(0.24f)

        assertEquals(Sentiment.NEUTRAL, SentimentMapper.map(neutralMin))
        assertEquals(Sentiment.NEUTRAL, SentimentMapper.map(neutralMax))
    }

    @Test
    fun testPositiveRange() {
        val positiveMin = getSentimentWith(0.25f)
        val positiveMax = getSentimentWith(1f)

        assertEquals(Sentiment.POSITIVE, SentimentMapper.map(positiveMin))
        assertEquals(Sentiment.POSITIVE, SentimentMapper.map(positiveMax))
    }

    @Test
    fun testInvalidValue() {
        val aboveMax = getSentimentWith(2f)
        val nullValue = getSentimentWith(null)

        assertEquals(null, SentimentMapper.map(aboveMax))
        assertEquals(null, SentimentMapper.map(nullValue))
    }

    private fun getSentimentWith(score: Float?): SentimentResponse {
        return SentimentResponse().apply {
            sentiment = DocumentSentiment(score = score)
        }
    }
}