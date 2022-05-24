package com.google.developers.teacup.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A Model class that holds information about the tea.
 * This class defines table for the Room database with primary key {@see #mCode}
 * @Entity
 */
@Entity
data class Tea(

    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @NonNull @ColumnInfo(name = "name")val name: String,
    @NonNull @ColumnInfo(name = "type")val type: String,
    @NonNull @ColumnInfo(name = "origin")val origin: String,
    @NonNull @ColumnInfo(name = "steepTimeMs")val steepTimeMs: Long,
    @NonNull @ColumnInfo(name = "description")val description: String,
    @NonNull @ColumnInfo(name = "ingredients")val ingredients: String,
    @NonNull @ColumnInfo(name = "caffeineLevel")val caffeineLevel: String,
    @NonNull @ColumnInfo(name = "isFavorite")val isFavorite: Boolean = false
)
