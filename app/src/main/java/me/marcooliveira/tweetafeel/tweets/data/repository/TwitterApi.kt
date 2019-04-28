package me.marcooliveira.tweetafeel.tweets.data.repository

import me.marcooliveira.tweetafeel.tweets.data.model.Tweet
import retrofit2.http.GET
import retrofit2.http.Header

interface TwitterApi {
    @GET("tweets")
    fun getTweets(@Header("Authorization") authorization: String): List<Tweet>
}