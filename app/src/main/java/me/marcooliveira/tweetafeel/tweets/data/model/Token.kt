package me.marcooliveira.tweetafeel.tweets.data.model

import com.google.gson.annotations.SerializedName

data class Token(@SerializedName("token_type")
                 var tokenType: String? = null,

                 @SerializedName("access_token")
                 var accessToken: String? = null)