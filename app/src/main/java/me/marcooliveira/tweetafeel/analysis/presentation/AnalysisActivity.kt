package me.marcooliveira.tweetafeel.analysis.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_analysis.*
import me.marcooliveira.tweetafeel.R
import me.marcooliveira.tweetafeel.core.Navigator
import me.marcooliveira.tweetafeel.core.PicassoCircleTransform

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


        viewModel.error.observe(this, Observer {
            // TODO: show error message
            Log.e("Tweetss", "error: $it")
        })
    }

    private fun init() {

    }

}