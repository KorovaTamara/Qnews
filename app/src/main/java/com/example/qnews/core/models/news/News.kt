package com.example.qnews.core.models.news

import android.os.Parcelable
import com.example.qnews.core.models.base.ListItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News(
    val id: String?,

    val name: String,

    val author: String?,

    val title: String,

    val description: String?,

    val url: String,

    val urlToImage: String?,

    val publishedAt: String,

    val content: String?
) : ListItem, Parcelable {
    var uniqueId: Int = 0

    override val itemId: Long
        get() = uniqueId.toLong()

    constructor(
        id: String?, name: String, author: String?, title: String, description: String?, url: String, urlToImage: String?, publishedAt: String,
        content: String?, uniqueId: Int
    ) : this(id, name, author, title, description, url, urlToImage, publishedAt, content) {
        this.uniqueId = uniqueId
    }
}

