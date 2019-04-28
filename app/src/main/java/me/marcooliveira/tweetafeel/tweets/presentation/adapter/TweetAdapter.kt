package me.marcooliveira.tweetafeel.tweets.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import me.marcooliveira.tweetafeel.R
import me.marcooliveira.tweetafeel.core.PicassoCircleTransform
import me.marcooliveira.tweetafeel.tweets.data.model.Tweet

class TweetAdapter(private val picasso: Picasso,
                   private val onClick: ((Tweet) -> Unit)) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val circleTransform by lazy { PicassoCircleTransform() }
    private val tweets = mutableListOf<Tweet>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tweet, parent, false)
        return TweetViewHolder(view, picasso, circleTransform) { tweet -> onClick.invoke(tweet) }
    }

    override fun getItemCount(): Int = tweets.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TweetViewHolder).bind(tweets[position])
    }

    fun setData(data: List<Tweet>) {
        tweets.clear()
        tweets.addAll(data)
        notifyDataSetChanged()
    }
}