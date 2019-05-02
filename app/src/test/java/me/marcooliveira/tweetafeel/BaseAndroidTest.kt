package me.marcooliveira.tweetafeel

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class,
    application = BaseAndroidTest.ApplicationStub::class,
    sdk = [28])
abstract class BaseAndroidTest {

    @Suppress("LeakingThis")
    @Rule
    @JvmField val injectMocks = TestRule { statement, _ ->
        MockitoAnnotations.initMocks(this@BaseAndroidTest)
        statement
    }

    fun context(): Context = RuntimeEnvironment.application

    fun activityContext(): Context = Mockito.mock(AppCompatActivity::class.java)

    internal class ApplicationStub : Application()
}