package me.marcooliveira.tweetafeel.analysis.data.model

import com.google.gson.annotations.SerializedName

data class SentimentResponse(@SerializedName("documentSentiment")
                             var sentiment: DocumentSentiment? = null,

                             @SerializedName("language")
                             var language: String? = null)