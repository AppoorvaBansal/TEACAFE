package com.google.developers.teacup.data

import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQueryBuilder

object SortUtils {

    enum class TeaSortBy {
        CAFFEINE, TYPE, DEFAULT
    }

    /**
     * A raw query at runtime to order by column for getting all the teas sorted.
     * @see RawQuery
     *
     *
     * @param sortBy a value from preferences to order by.
     * @return SimpleSQLiteQuery with a correct order by column name.
     */
    fun getAllQuery(sortBy: TeaSortBy, showOnlyFavorites: Boolean): SimpleSQLiteQuery {
        val queryBuilder = SupportSQLiteQueryBuilder
                .builder(DataTeaNames.TABLE_NAME)
                .orderBy(getSortColumn(sortBy))
        if (showOnlyFavorites) {
            queryBuilder.selection(DataTeaNames.COL_FAVORITE, arrayOf("1"))
        }
        return SimpleSQLiteQuery(queryBuilder.create().sql)
    }

    /**
     * Get a column name in from a preference value.
     */
    private fun getSortColumn(value: TeaSortBy): String {
        return when (value) {
            TeaSortBy.CAFFEINE -> DataTeaNames.COL_CAFFEINE
            TeaSortBy.TYPE -> DataTeaNames.COL_TYPE
            else -> DataTeaNames.COL_NAME
        }
    }
}
