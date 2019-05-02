package me.marcooliveira.tweetafeel.main.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import me.marcooliveira.tweetafeel.R
import me.marcooliveira.tweetafeel.core.Navigator

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
    }

    private fun setupView() {
        viewTweetsButton.setOnClickListener {
            val user = twitterUser.text.toString()
            Navigator.TweetList.launch(this, user)
        }

        twitterUser.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val hasContent = !s.isNullOrBlank()
                viewTweetsButton.isEnabled = hasContent
            }
        })
    }
}