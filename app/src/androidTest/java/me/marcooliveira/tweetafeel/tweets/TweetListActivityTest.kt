package me.marcooliveira.tweetafeel.tweets

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.truth.content.IntentSubject
import androidx.test.runner.AndroidJUnit4
import me.marcooliveira.tweetafeel.R
import me.marcooliveira.tweetafeel.analysis.presentation.AnalysisActivity
import me.marcooliveira.tweetafeel.main.presentation.MainActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TweetListActivityTest {

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.twitterUser)).perform(ViewActions.typeText("twitter"))
        onView(withId(R.id.viewTweetsButton)).perform(click())
    }

    @Test
    fun showLoadingViewWhileFetching() {
        onView(withId(R.id.tweetListLoadingView)).check(matches(isDisplayed()))
    }

    @Test
    fun showContentAfterLoad() {
        Thread.sleep(5000)
        onView(withId(R.id.tweetList)).check(matches(isDisplayed()))
    }

    @Test
    fun launchAnalysisOnTweetClick() {
        Thread.sleep(5000)
        Intents.init()
        onView(withId(R.id.tweetList)).perform(click())

        IntentSubject.assertThat(Intents.getIntents().first()).hasComponentClass(AnalysisActivity::class.java)
        Intents.release()
    }
}