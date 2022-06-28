package com.example.myfootballlife.data.models.twitterapi.tweets

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/** https://developer.twitter.com/en/docs/twitter-api/data-dictionary/object-model/tweet */
@Entity
data class Tweets(
    @PrimaryKey val id:String = "",
    val text:String = "",
    @ColumnInfo(name = "author_id") val authorId:String = "",
    @ColumnInfo(name = "created_at") val createdAt:String = "",
    val lang:String = ""
)