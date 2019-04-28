package me.marcooliveira.tweetafeel.tweets.domain

import me.marcooliveira.tweetafeel.core.Constants.TWITTER_AUTH_TOKEN
import me.marcooliveira.tweetafeel.core.Either
import me.marcooliveira.tweetafeel.core.Error
import me.marcooliveira.tweetafeel.tweets.data.model.Tweet
import me.marcooliveira.tweetafeel.tweets.data.repository.TwitterService

class GetTweets {

    suspend fun execute(userId: String): Either<Error, List<Tweet>> {
        return try {
            val token = "Bearer " + TwitterService.getTokenAsync(TWITTER_AUTH_TOKEN).await().accessToken
            val tweets = TwitterService.getTweetsAsync(token, userId).await()
            Either.Value(tweets)
        } catch (e: Exception) {
            Either.Error(Error(e.message))
        }
    }
}