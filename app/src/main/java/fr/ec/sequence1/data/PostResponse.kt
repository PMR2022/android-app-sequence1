package fr.ec.sequence1.data


data class PostResponse(
    val id : Long,
    val name : String,
    val tagline : String,
    val thumbnail : Thumbnail
    )