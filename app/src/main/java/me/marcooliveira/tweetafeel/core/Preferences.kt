package me.marcooliveira.tweetafeel.core

import android.content.Context
import android.content.SharedPreferences
import me.marcooliveira.tweetafeel.core.Constants.PREFS_FILE
import me.marcooliveira.tweetafeel.core.Constants.PREFS_TWITTER_TOKEN

class Preferences(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILE, 0)

    var twitterToken: String?
        get() = prefs.getString(PREFS_TWITTER_TOKEN, null)
        set(value) = prefs.edit().putString(PREFS_TWITTER_TOKEN, value).apply()
}