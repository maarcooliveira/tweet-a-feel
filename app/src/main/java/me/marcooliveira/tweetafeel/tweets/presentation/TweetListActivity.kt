package me.marcooliveira.tweetafeel.tweets.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.afollestad.materialdialogs.MaterialDialog
import com.crashlytics.android.Crashlytics
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_tweet_list.*
import me.marcooliveira.tweetafeel.R
import me.marcooliveira.tweetafeel.core.LoadingException
import me.marcooliveira.tweetafeel.core.Navigator
import me.marcooliveira.tweetafeel.tweets.data.model.Tweet
import me.marcooliveira.tweetafeel.tweets.presentation.adapter.TweetAdapter

class TweetListActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(TweetListViewModel::class.java) }
    private val picasso by lazy { Picasso.Builder(this).loggingEnabled(true).build() }
    private val adapter by lazy { TweetAdapter(picasso, onClick) }
    private val twitterUser by lazy { Navigator.TweetList.twitterUser(this) }
    private val onClick: ((Tweet) -> Unit) = {
        Navigator.Analysis.launch(this, it)
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
            adapter.setData(it)
        })

        viewModel.error.observe(this, Observer {
            MaterialDialog(this).show {
                message(R.string.tweets_loading_error)
                positiveButton(R.string.button_retry) {
                    init()
                }
                negativeButton(R.string.button_cancel)
            }
            Crashlytics.logException(LoadingException(it))
        })
    }

    private fun init() {
        viewModel.loadTweets("maarcooliveira")
    }

}
