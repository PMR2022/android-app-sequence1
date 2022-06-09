package fr.ec.sequence1.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post(
    @PrimaryKey
    val id: Long,
    val title: String,
    val subtitle: String,
    val imageUrl: String
)
