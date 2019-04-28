package me.marcooliveira.tweetafeel.tweets.data.model

import com.google.gson.annotations.SerializedName

data class User(@SerializedName("id_str")
                var id: String? = null,

                @SerializedName("name")
                var name: String? = null,

                @SerializedName("screen_name")
                var user: String? = null,

                @SerializedName("profile_image_url")
                var imageUrl: String? = null)