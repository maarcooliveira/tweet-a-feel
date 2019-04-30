package me.marcooliveira.tweetafeel.core

import android.app.Activity
import android.content.Intent
import me.marcooliveira.tweetafeel.analysis.presentation.AnalysisActivity
import me.marcooliveira.tweetafeel.tweets.data.model.Tweet
import me.marcooliveira.tweetafeel.tweets.presentation.TweetListActivity

internal object Navigator {

    internal object TweetList {
        private const val TWITTER_USER = "user"

        fun launch(activity: Activity, twitterUser: String) {
            val intent = Intent(activity, TweetListActivity::class.java).apply {
                putExtra(TWITTER_USER, twitterUser)
            }
            activity.startActivity(intent)
        }

        fun twitterUser(activity: TweetListActivity): String? = activity.intent.getStringExtra(TWITTER_USER)
    }

    internal object Analysis {
        private const val TWITTER_USER = "user"
        private const val USER_NAME = "name"
        private const val TWEET = "tweet"
        private const val PICTURE = "picture"

        fun launch(activity: Activity, tweet: Tweet) {
            val intent = Intent(activity, AnalysisActivity::class.java).apply {
                putExtra(TWITTER_USER, tweet.user?.user)
                putExtra(USER_NAME, tweet.user?.name)
                putExtra(TWEET, tweet.text)
                putExtra(PICTURE, tweet.user?.imageUrl)
            }
            activity.startActivity(intent)
        }

        fun twitterUser(activity: AnalysisActivity): String? = activity.intent.getStringExtra(TWITTER_USER)
        fun name(activity: AnalysisActivity): String? = activity.intent.getStringExtra(USER_NAME)
        fun tweet(activity: AnalysisActivity): String? = activity.intent.getStringExtra(TWEET)
        fun picture(activity: AnalysisActivity): String? = activity.intent.getStringExtra(PICTURE)
    }
}