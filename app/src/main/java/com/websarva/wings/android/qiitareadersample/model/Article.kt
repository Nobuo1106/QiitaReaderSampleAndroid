package com.websarva.wings.android.qiitareadersample.model

import com.google.gson.annotations.SerializedName

data class Article(
    var title: String,
    @SerializedName("user") var user: User,
    var url: String,
    @SerializedName("created_at") var date: String
)
