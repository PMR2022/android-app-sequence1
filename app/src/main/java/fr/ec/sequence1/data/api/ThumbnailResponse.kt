package fr.ec.sequence1.data.api

import com.google.gson.annotations.SerializedName

data class ThumbnailResponse(
    @SerializedName("image_url")
    val imageUrl : String
)
