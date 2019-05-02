package me.marcooliveira.tweetafeel.tweets.domain

import me.marcooliveira.tweetafeel.core.Constants.TWITTER_AUTH_TOKEN
import me.marcooliveira.tweetafeel.core.Either
import me.marcooliveira.tweetafeel.core.Error
import me.marcooliveira.tweetafeel.core.Preferences
import me.marcooliveira.tweetafeel.tweets.data.model.Tweet
import me.marcooliveira.tweetafeel.tweets.data.repository.TwitterService

class GetTweets(private val service: TwitterService) {

    suspend fun execute(userId: String, prefs: Preferences): Either<Error, List<Tweet>> {
        return try {
            if (prefs.twitterToken.isNullOrBlank()) {
                prefs.twitterToken = "Bearer " + service.getTokenAsync(TWITTER_AUTH_TOKEN).await().accessToken
            }
            val tweets = service.getTweetsAsync(prefs.twitterToken!!, userId).await()
            Either.Value(tweets)
        } catch (e: Exception) {
            Either.Error(Error(e.message))
        }
    }
}