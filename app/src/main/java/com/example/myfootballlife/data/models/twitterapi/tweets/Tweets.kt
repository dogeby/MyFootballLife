package com.example.myfootballlife.data.models.twitterapi.tweets

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/** https://developer.twitter.com/en/docs/twitter-api/data-dictionary/object-model/tweet */
@Entity
data class Tweets(
    @PrimaryKey @SerializedName("id") val id:String,
    @SerializedName("text") val text:String,
    @ColumnInfo(name = "author_id") @SerializedName("author_id") val authorId:String,
    @ColumnInfo(name = "created_at") @SerializedName("created_at") val createdAt:String,
    @SerializedName("lang") val lang:String
)