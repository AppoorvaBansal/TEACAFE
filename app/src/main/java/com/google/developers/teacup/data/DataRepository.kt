package com.google.developers.teacup.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.google.developers.teacup.util.executeThread

/**
 * Handles data sources and execute on the correct thread.
 */
class DataRepository(private val dao: TeaDao) {

    fun getSortedTeas(sort: String, fileByFavorite: Boolean = false): LiveData<PagedList<Tea>> {
        val sortBy = SortUtils.TeaSortBy.valueOf(sort)
        val factory = dao.getAll(SortUtils.getAllQuery(sortBy, fileByFavorite))
        return LivePagedListBuilder(factory, PAGE_SIZE)
                .build()
    }

    fun insert(tea: Tea) = executeThread {
        dao.insert(tea)
    }

    fun delete(tea: Tea) = executeThread {
        dao.delete(tea)
    }

    fun getTea(name: String): LiveData<Tea> {
        return dao.getTea(name)
    }

    fun getRandomTea(): Tea {
        return dao.getRandomTea()
    }

    fun getTeaOriginCountries(): LiveData<List<String>> {
        return dao.getTeaOrigins()
    }

    fun getTeaTypes(): LiveData<List<String>> {
        return dao.getTeaTypes()
    }
    fun getsteeptime(): LiveData<List<Long>> {
        return dao.getsteepTimeMs()
    }

    fun searchTeas(location: String, teaType: String, steepTime: Int): LiveData<PagedList<Tea>> {
        val factory = dao.searchTeas(location,teaType,steepTime)
        return LivePagedListBuilder(factory, PAGE_SIZE)
            .build()
    }

    fun updateFavorite(teaName: String) {
return dao.updateFavorite(teaName)
    }

    companion object {
        @Volatile
        private var instance: DataRepository? = null
        private const val PAGE_SIZE = 20

        fun getInstance(context: Context): DataRepository? {
            return instance ?: synchronized(DataRepository::class.java) {
                if (instance == null) {
                    val database = TeaDatabase.getInstance(context)
                    instance = DataRepository(database.teaDao())
                }
                return instance
            }
        }
    }
}
