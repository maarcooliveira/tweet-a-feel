package me.marcooliveira.tweetafeel.core

import android.app.Application
import android.util.Log
import com.twitter.sdk.android.core.DefaultLogger
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.TwitterConfig

class CustomApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        initTwitter()

    }

    private fun initTwitter() {
        val config = TwitterConfig.Builder(this)
            .logger(DefaultLogger(Log.DEBUG))
            .twitterAuthConfig(TwitterAuthConfig("9NUwLaUTKjKUJHW7IRQA", "Ui14aml6xuPrxPNoCcUBPJUn8Vf2erbLQfqNbwkO0"))
            .debug(true)
            .build()
        Twitter.initialize(config)

//        TwitterCore.getInstance()
    }
}