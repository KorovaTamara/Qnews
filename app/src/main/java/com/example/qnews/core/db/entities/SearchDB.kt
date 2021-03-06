package com.example.qnews.core.db.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.qnews.core.models.base.ListItem
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "recent_search_table")
@Parcelize
data class SearchDB(
    @ColumnInfo(name = "search")
    val search: String
) : Parcelable, ListItem {

    @PrimaryKey(autoGenerate = true)
    var key: Int = 0

    constructor(search: String, key: Int) : this(search) {
        this.key = key
    }

    override fun toString(): String = search

    override val itemId: Long
        get() = key.toLong()
}