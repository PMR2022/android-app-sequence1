package fr.ec.sequence1.data.database

import android.content.ContentValues
import android.content.Context
import fr.ec.sequence1.data.Post

class PostDao(context: Context) {

    private val productHuntDbHelper = ProductHuntDbHelper(context)

    fun save(post: Post): Long {
        return productHuntDbHelper.writableDatabase
            .insert(
                DataBaseContract.PostTable.TABLE_NAME,
                null,
                post.toContentValues()
            )
    }


    private fun Post.toContentValues(): ContentValues {

        val contentValues = ContentValues()
        contentValues.put(DataBaseContract.PostTable.ID_COLUMN, this.id)
        contentValues.put(DataBaseContract.PostTable.TITLE_COLUMN, this.title)
        contentValues.put(DataBaseContract.PostTable.SUBTITLE_COLUMN, this.subtitle)
        contentValues.put(DataBaseContract.PostTable.IMAGE_URL_COLUMN, this.imageUrl)

        return contentValues
    }

    fun retrievePosts(): List<Post> {
        val cursor = productHuntDbHelper.readableDatabase
            .query(
                DataBaseContract.PostTable.TABLE_NAME,
                DataBaseContract.PostTable.PROJECTIONS,
                null,
                null,
                null,
                null,
                null
            )
        val posts: MutableList<Post> = mutableListOf()
        if (cursor.moveToFirst()) {
            do {
                val post = Post(
                    id = cursor.getLong(0),
                    title = cursor.getString(1),
                    subtitle = cursor.getString(2),
                    imageUrl = cursor.getString(3)
                )
                posts.add(post)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return posts
    }

}