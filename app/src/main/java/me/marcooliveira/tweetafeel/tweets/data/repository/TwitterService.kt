package me.marcooliveira.tweetafeel.tweets.data.repository

import kotlinx.coroutines.Deferred
import me.marcooliveira.tweetafeel.core.Service
import me.marcooliveira.tweetafeel.tweets.data.model.Token
import me.marcooliveira.tweetafeel.tweets.data.model.Tweet

internal object TwitterService: Service<TwitterApi> {

    fun getTweetsAsync(token: String, userId: String): Deferred<List<Tweet>> {
        return retrofit("https://api.twitter.com/", TwitterApi::class.java)
            .getTweets(token, userId, 5)
    }

    fun getTokenAsync(authorization: String): Deferred<Token> {
        return retrofit("https://api.twitter.com/", TwitterApi::class.java)
            .getToken(authorization, "application/x-www-form-urlencoded", "client_credentials")
    }
}