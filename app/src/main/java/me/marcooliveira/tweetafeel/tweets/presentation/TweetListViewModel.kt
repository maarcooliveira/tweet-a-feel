package me.marcooliveira.tweetafeel.tweets.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import me.marcooliveira.tweetafeel.tweets.data.model.Tweet

class TweetListViewModel(application: Application): AndroidViewModel(application) {

    internal val tweets = MutableLiveData<List<Tweet>>()
    internal val error = MutableLiveData<String>()

    fun loadTweets(user: String?) {

    }
}