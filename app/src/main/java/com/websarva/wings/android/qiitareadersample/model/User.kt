package com.websarva.wings.android.qiitareadersample.model

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("id") var id: String,
    @SerializedName("profile_image_url") var imgUrl: String
)