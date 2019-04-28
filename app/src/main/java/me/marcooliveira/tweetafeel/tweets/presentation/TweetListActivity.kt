package me.marcooliveira.tweetafeel.tweets.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_tweet_list.*
import me.marcooliveira.tweetafeel.R
import me.marcooliveira.tweetafeel.tweets.data.model.Tweet
import me.marcooliveira.tweetafeel.tweets.presentation.adapter.TweetAdapter

class TweetListActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(TweetListViewModel::class.java) }
    private val picasso by lazy { Picasso.Builder(this).build() }
    private val adapter by lazy { TweetAdapter(picasso, onClick) }
    private val onClick: ((Tweet) -> Unit) = {
        // TODO launch details screen
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweet_list)

        setupView()
        setupListeners()
        init()
    }

    private fun setupView() {
        tweetList.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        tweetList.adapter = adapter
    }

    private fun setupListeners() {

        viewModel.tweets.observe(this, Observer {
            Log.e("Tweetss", "received $it")
            adapter.setData(it)
        })

        viewModel.error.observe(this, Observer {
            Log.e("Tweetss", "error: $it")
        })
    }

    private fun init() {
        viewModel.loadTweets("maarcooliveira")
    }

}
