package me.marcooliveira.tweetafeel.core

import android.view.View

fun View.switchVisibility(show: Boolean? = null) {
    val isToShow = show ?: (this.visibility == View.GONE)
    this.visibility = if (isToShow) View.VISIBLE else View.GONE
}