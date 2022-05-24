package com.google.developers.teacup.data

/**
 * Database naming schema
 * more information {@see app/schemas/tea.json}
 */
object DataTeaNames {

    const val TABLE_NAME = "tea"

    /**
     * Column name for Tea id and used as primary
     */
    const val COL_ID = "id"

    /**
     * Column name for Tea name
     */
    const val COL_NAME = "name"

    /**
     * Column name for Tea type
     */
    const val COL_TYPE = "type"

    /**
     * Column name for Tea origin
     */
    const val COL_ORIGIN = "origin"

    /**
     * Column name for steep time Tea
     */
    const val COL_STEEP_TIME = "steep_time"

    /**
     * Column name for Tea description
     */
    const val COL_DESCRIPTION = "description"

    /**
     * Column name for Tea ingredients
     */
    const val COL_INGREDIENTS = "ingredients"

    /**
     * Column name for Tea caffeine level
     */
    const val COL_CAFFEINE = "caffeine"

    /**
     * Column name for Tea favorite
     */
    const val COL_FAVORITE = "favorite"
}
