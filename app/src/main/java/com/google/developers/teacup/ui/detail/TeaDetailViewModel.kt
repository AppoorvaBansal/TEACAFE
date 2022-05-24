package com.google.developers.teacup.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.developers.teacup.data.DataRepository
import com.google.developers.teacup.data.Tea
import com.google.developers.teacup.data.TeaType

/**
 * ViewModel for Detail activity and steep timer activity.
 */
class TeaDetailViewModel(
    private val repository: DataRepository,
    private val teaName: String
) : ViewModel() {

    val tea: LiveData<Tea> = repository.getTea(teaName)
    private val teaDetail = MutableLiveData<TeaDetailModel>()

    val teaImage: LiveData<TeaDetailModel>
        get() = teaDetail

    fun setFavorite() {
        repository.updateFavorite(teaName)
    }

    fun setTeaImage(teaCategory: String) {
        val teaType = TeaType.findByName(teaCategory)
        teaDetail.value = TeaDetailModel(teaType.value)
    }
}
