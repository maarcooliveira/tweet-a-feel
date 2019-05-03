package me.marcooliveira.tweetafeel.main

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.getIntents
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.truth.content.IntentSubject.assertThat
import androidx.test.runner.AndroidJUnit4
import me.marcooliveira.tweetafeel.R
import me.marcooliveira.tweetafeel.main.presentation.MainActivity
import me.marcooliveira.tweetafeel.tweets.presentation.TweetListActivity
import org.hamcrest.CoreMatchers.not
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun showTweetsOnUserIdConfirm() {
        ActivityScenario.launch(MainActivity::class.java)
        Intents.init()

        onView(withId(R.id.twitterUser)).perform(typeText("twitter"))
        onView(withId(R.id.viewTweetsButton)).perform(click())

        assertThat(getIntents().first()).hasComponentClass(TweetListActivity::class.java)
        Intents.release()
    }

    @Test
    fun disableButtonForEmptyUserInput() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.twitterUser)).perform(typeText(""))
        onView(withId(R.id.viewTweetsButton)).check(matches(not(isEnabled())))
    }

    @Test
    fun enableButtonForNonEmptyUserInput() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.twitterUser)).perform(typeText("twitter"))
        onView(withId(R.id.viewTweetsButton)).check(matches(isEnabled()))
    }
}