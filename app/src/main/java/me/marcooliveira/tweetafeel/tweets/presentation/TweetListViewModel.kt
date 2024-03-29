package me.marcooliveira.tweetafeel.tweets.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.marcooliveira.tweetafeel.core.Error
import me.marcooliveira.tweetafeel.core.prefs
import me.marcooliveira.tweetafeel.tweets.data.model.Tweet
import me.marcooliveira.tweetafeel.tweets.data.repository.TwitterService
import me.marcooliveira.tweetafeel.tweets.domain.GetTweets

class TweetListViewModel(application: Application): AndroidViewModel(application) {

    private val getTweets by lazy { GetTweets(TwitterService()) }
    private val prefs by lazy { application.prefs() }
    internal val tweets = MutableLiveData<List<Tweet>>()
    internal val error = MutableLiveData<String?>()

    fun loadTweets(user: String) {
        viewModelScope.launch {
            val result = getTweets.execute(user, prefs)

            withContext(Dispatchers.Main) {
                result.either(::handleError, ::handleTweets)
            }
        }
    }

    private fun handleTweets(newTweets: List<Tweet>) {
        tweets.postValue(newTweets)
    }

    private fun handleError(errorResponse: Error?) {
        error.postValue(errorResponse?.message)
    }
}