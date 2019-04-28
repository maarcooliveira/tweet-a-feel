package me.marcooliveira.tweetafeel.tweets.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import me.marcooliveira.tweetafeel.R

class TweetListActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(TweetListViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweet_list)

        setupListeners()
        init()
    }

    private fun setupListeners() {

        viewModel.tweets.observe(this, Observer {
            Log.e("Tweetss", "received $it")
        })

        viewModel.error.observe(this, Observer {
            Log.e("Tweetss", "error: $it")
        })
    }

    private fun init() {
        viewModel.loadTweets("maarcooliveira")
    }

}
