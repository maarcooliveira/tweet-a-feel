package me.marcooliveira.tweetafeel.analysis.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.afollestad.materialdialogs.MaterialDialog
import com.crashlytics.android.Crashlytics
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_analysis.*
import me.marcooliveira.tweetafeel.R
import me.marcooliveira.tweetafeel.core.LoadingException
import me.marcooliveira.tweetafeel.core.Navigator
import me.marcooliveira.tweetafeel.core.PicassoCircleTransform
import me.marcooliveira.tweetafeel.core.switchVisibility

class AnalysisActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(AnalysisViewModel::class.java) }
    private val picasso by lazy { Picasso.Builder(this).loggingEnabled(true).build() }
    private val userHandle by lazy { Navigator.Analysis.twitterUser(this) }
    private val userName by lazy { Navigator.Analysis.name(this) }
    private val userPicture by lazy { Navigator.Analysis.picture(this) }
    private val tweetContent by lazy { Navigator.Analysis.tweet(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analysis)

        setupView()
        setupListeners()
        init()
    }

    private fun setupView() {
        name.text = userName
        handle.text = getString(R.string.user_handle, userHandle)
        tweet.text = tweetContent
        picasso.load(userPicture)
            .transform(PicassoCircleTransform())
            .into(picture)
    }

    private fun setupListeners() {
        viewModel.sentiment.observe(this, Observer {
            emojiLoading.stop()
            emojiLoading.switchVisibility(false)
            emoji.switchVisibility(true)
            emoji.text = getString(it.emoji)
            container.setBackgroundResource(it.background)
            window.statusBarColor = ContextCompat.getColor(this, it.background)
        })

        viewModel.error.observe(this, Observer {
            MaterialDialog(this).show {
                message(R.string.sentiment_loading_error)
                positiveButton(R.string.button_retry) {
                    init()
                }
                negativeButton(R.string.button_cancel) {
                    finish()
                }
            }
            Crashlytics.logException(LoadingException(it))
        })
    }

    private fun init() {
        tweetContent?.let {
            viewModel.analyseTweet(it)
        }
    }

}