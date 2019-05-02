package me.marcooliveira.tweetafeel.tweets.domain

import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import me.marcooliveira.tweetafeel.BaseUnitTest
import me.marcooliveira.tweetafeel.core.Preferences
import me.marcooliveira.tweetafeel.tweets.data.model.Token
import me.marcooliveira.tweetafeel.tweets.data.model.Tweet
import me.marcooliveira.tweetafeel.tweets.data.model.User
import me.marcooliveira.tweetafeel.tweets.data.repository.TwitterService
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString


class GetTweetsTest: BaseUnitTest() {

    private val preferences: Preferences = mock()
    private val service: TwitterService = mock()

    @Test
    fun getTweetsWithoutToken_requestOAuthTokenFirst() {
        val userId = "twitter"

        whenever(preferences.twitterToken).thenReturn(null)
        runBlocking { GetTweets(service).execute(userId, preferences) }

        verify(service, times(1)).getTokenAsync(anyString())
    }

    @Test
    fun getTweetsWithToken_doNotRequestToken() {
        val token = "abc"
        val userId = "twitter"
        val tweets = listOf(Tweet(user = User()))

        whenever(preferences.twitterToken).thenReturn(token)
        whenever(service.getTweetsAsync(anyString(), anyString())).thenReturn(CompletableDeferred(tweets))
        runBlocking { GetTweets(service).execute(userId, preferences) }

        verify(service, never()).getTokenAsync(anyString())
        verify(service, times(1)).getTweetsAsync(token, userId)
    }

    @Test
    fun getTweetsWithoutToken_saveTokenOnPreferences() {
        val userId = "twitter"
        val token = Token(accessToken = "abc")

        whenever(preferences.twitterToken).thenReturn(null)
        whenever(service.getTokenAsync(anyString())).thenReturn(CompletableDeferred(token))
        runBlocking { GetTweets(service).execute(userId, preferences) }

        verify(preferences, times(1)).twitterToken = "Bearer ${token.accessToken}"
    }
}