package me.marcooliveira.tweetafeel.analysis.presentation

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import me.marcooliveira.tweetafeel.R

enum class Sentiment(@StringRes val emoji: Int, @ColorRes val background: Int) {
    POSITIVE(R.string.sentiment_positive, R.color.sentiment_positive),
    NEGATIVE(R.string.sentiment_negative, R.color.sentiment_negative),
    NEUTRAL(R.string.sentiment_neutral, R.color.sentiment_neutral)
}