package com.github.vikie1.courseapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val topicName: Int,
    val courseCount: Int,
    @DrawableRes val topicImageResource: Int
)