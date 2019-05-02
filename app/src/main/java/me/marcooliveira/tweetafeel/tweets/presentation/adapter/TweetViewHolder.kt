package me.marcooliveira.tweetafeel.tweets.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_tweet.view.*
import me.marcooliveira.tweetafeel.R
import me.marcooliveira.tweetafeel.core.PicassoCircleTransform
import me.marcooliveira.tweetafeel.tweets.data.model.Tweet

class TweetViewHolder(view: View,
                      private val picasso: Picasso,
                      private val circleTransform: PicassoCircleTransform,
                      private val onClick: ((Tweet) -> Unit)): RecyclerView.ViewHolder(view) {

    private val picture = view.picture
    private val name = view.name
    private val handle = view.handle
    private val tweetContent = view.tweet
    private val container = view.container

    fun bind(tweet: Tweet) {
        name.text = tweet.user?.name
        handle.text = handle.context.getString(R.string.user_handle, tweet.user?.user)
        tweetContent.text = tweet.text

        picasso.load(tweet.user?.imageUrl)
            .transform(circleTransform)
            .into(picture)

        container.setOnClickListener {
            onClick.invoke(tweet)
        }
    }
}