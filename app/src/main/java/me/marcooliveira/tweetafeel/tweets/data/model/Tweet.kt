package me.marcooliveira.tweetafeel.tweets.data.model

import com.google.gson.annotations.SerializedName

data class Tweet(@SerializedName("id_str")
                 var id: String? = null,

                 @SerializedName("text")
                 var text: String? = null,

                 @SerializedName("created_at")
                 var date: String? = null,

                 @SerializedName("retweet_count")
                 var retweets: Int? = null,

                 @SerializedName("favorite_count")
                 var favorites: Int? = null,

                 @SerializedName("lang") // 'en'; 'und' if not identified
                 var language: String? = null,

                 @SerializedName("user")
                 var user: User? = null)