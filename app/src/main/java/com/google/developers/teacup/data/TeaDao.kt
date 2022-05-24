package com.google.developers.teacup.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
//import androidx.room.Delete
//import androidx.room.Query
import androidx.sqlite.db.SupportSQLiteQuery

/**
 * Room data access object for storing and querying teas by various criteria.
 * @see //Dao
 */
@Dao
interface TeaDao {

    /**
     * Returns a random Tea
     */
    @Query("SELECT * FROM tea ORDER BY RANDOM() LIMIT 1")
    fun getRandomTea(): Tea

    /**
     * Returns all data in table for Paging
     *
     * @param query a dynamic SQL query
     */
    //@Query("SELECT * FROM tea ORDER BY name ASC")
    @RawQuery(observedEntities = [Tea::class])
    fun getAll(query: SupportSQLiteQuery): DataSource.Factory<Int, Tea>

    /**
     * Returns a Tea based on the tea name.
     *
     * @param name of a tea
     */
    @Query("SELECT * FROM tea WHERE name == :name")
    fun getTea(name: String): LiveData<Tea>

    /**
     * Update tea if its favorite or not.
     *
     * @param name of a tea
     */
    @Query("UPDATE tea SET isFavorite = NOT isFavorite WHERE name == :name")
    fun updateFavorite(name: String)

    /**
     * Find teas by the location, type, and maximum steep time.
     */
    @Query("SELECT * FROM tea where origin==:location and type==:teaType and steepTimeMs==:steepTime ")
    fun searchTeas(location: String, teaType: String, steepTime: Int): DataSource.Factory<Int, Tea>

    /**
     * Find teas by tea type and maximum steep time.
     */
    @Query("SELECT * FROM tea where type==:teaType and steepTimeMs==:steepTime ")
    fun searchTeas(teaType: String, steepTime: Int): DataSource.Factory<Int, Tea>

    /**
     * Find tea by maximum steep time.
     */
    @Query("SELECT * FROM tea where steepTimeMs==:steepTime ")
    fun searchTeas(steepTime: Int): DataSource.Factory<Int, Tea>

    /**
     * Find tea by location.
     */
    @Query("SELECT * FROM tea where origin==:location")
    fun searchTeas(location: String): DataSource.Factory<Int, Tea>

    /**
     * Find origin locations of teas.
     */
    @Query("SELECT origin FROM tea")
    fun getTeaOrigins(): LiveData<List<String>>

    /**
     * Get a list of tea types
     */
    @Query("SELECT type FROM tea")
    fun getTeaTypes(): LiveData<List<String>>

    @Query("SELECT steepTimeMs FROM tea")
    fun getsteepTimeMs(): LiveData<List<Long>>

    @Insert
    fun insert(vararg tea: Tea)

    @Delete
    fun delete(tea: Tea)
}
