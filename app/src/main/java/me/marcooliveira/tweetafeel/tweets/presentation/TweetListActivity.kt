package me.marcooliveira.tweetafeel.tweets.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import me.marcooliveira.tweetafeel.R

class TweetListActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(TweetListViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweet_list)
    }


}
