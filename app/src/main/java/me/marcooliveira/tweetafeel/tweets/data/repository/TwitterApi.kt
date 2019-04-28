package me.marcooliveira.tweetafeel.tweets.data.repository

import kotlinx.coroutines.Deferred
import me.marcooliveira.tweetafeel.tweets.data.model.Token
import me.marcooliveira.tweetafeel.tweets.data.model.Tweet
import retrofit2.http.*

interface TwitterApi {
    @GET("1.1/statuses/user_timeline.json")
    fun getTweets(@Header("Authorization") token: String,
                  @Query("screen_name") userName: String,
                  @Query("count") count: Int): Deferred<List<Tweet>>

    @POST("oauth2/token")
    @FormUrlEncoded
    fun getToken(@Header("Authorization") authorization: String,
                 @Header("Content-Type") contentType: String,
                 @Field("grant_type") grantType: String): Deferred<Token>
}