package com.margarita_pekutovskaya.k_beautylab.data.model

import com.google.gson.annotations.SerializedName

data class CosmeticItem(
    @SerializedName("name")
    val name:String,
    @SerializedName("image_link")
    val imageLink:String,
    @SerializedName("description")
    val description:String,
)
