package com.google.developers.teacup.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.developers.teacup.data.DataRepository
import com.google.developers.teacup.data.Tea
import com.google.developers.teacup.util.Event

/**
 * ViewModel for the List of teas
 */
enum class FilterType {
    NONE, FAVORITES, SEARCH_RESULTS
}
class TeaListViewModel(private val repository: DataRepository, val sortBy: String) : ViewModel() {
    private val favoriteFilter = MutableLiveData<Boolean>()
    private val undoDelete = MutableLiveData<Event<Tea>>()
    var teaFilter = MutableLiveData<FilterType>()
    var searchOrigin: String = ""
    var searchTeaType: String = ""


    val undo: LiveData<Event<Tea>>
        get() = undoDelete

    init {
        teaFilter.value = FilterType.NONE
        favoriteFilter.value = false
    }

    /**
     * Three-way toggle for filtering
     */
    fun toggleFilter() {
        when (teaFilter.value) {
            FilterType.NONE -> teaFilter.value = FilterType.FAVORITES
            FilterType.FAVORITES -> teaFilter.value = FilterType.SEARCH_RESULTS
            FilterType.SEARCH_RESULTS -> teaFilter.value = FilterType.NONE
            else -> teaFilter.value = FilterType.FAVORITES
        }
    }

    fun delete(tea: Tea) {
        repository.delete(tea)
        undoDelete.value = Event(tea)
    }
}