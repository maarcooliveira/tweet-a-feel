package me.marcooliveira.tweetafeel.analysis.data.model

import com.google.gson.annotations.SerializedName

data class Document(@SerializedName("type")
                    var type: String? = null,

                    @SerializedName("content")
                    var content: String? = null)