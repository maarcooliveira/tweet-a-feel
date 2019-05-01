package me.marcooliveira.tweetafeel.analysis.data.model

import com.google.gson.annotations.SerializedName

data class SentimentRequestBody(@SerializedName("document")
                                var document: Document? = null,

                                @SerializedName("encodingType")
                                var encoding: String? = null)