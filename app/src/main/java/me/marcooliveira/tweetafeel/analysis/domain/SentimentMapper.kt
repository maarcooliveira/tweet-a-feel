package me.marcooliveira.tweetafeel.analysis.domain

import me.marcooliveira.tweetafeel.analysis.data.model.SentimentResponse
import me.marcooliveira.tweetafeel.analysis.presentation.Sentiment

internal object SentimentMapper {
    fun map(sentiment: SentimentResponse): Sentiment? {
        val score = sentiment.sentiment?.score
        return when {
            score == null -> null
            score < -0.25 -> Sentiment.NEGATIVE
            score < 0.25 -> Sentiment.NEUTRAL
            score <= 1 -> Sentiment.POSITIVE
            else -> null
        }
    }
}