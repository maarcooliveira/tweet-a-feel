package me.marcooliveira.tweetafeel.core

import android.app.Application
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric

class CustomApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        Fabric.with(this, Crashlytics())
        prefs().twitterToken = null
    }
}