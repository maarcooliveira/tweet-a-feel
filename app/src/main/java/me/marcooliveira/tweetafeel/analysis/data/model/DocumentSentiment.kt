package me.marcooliveira.tweetafeel.analysis.data.model

import com.google.gson.annotations.SerializedName

data class DocumentSentiment(@SerializedName("magnitude")
                             var magnitude: Float? = null,

                            @SerializedName("score")
                            var score: Float? = null)