package com.google.developers.teacup.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.developers.teacup.data.DataRepository
import com.google.developers.teacup.data.Tea

class TeaViewModel(repository: DataRepository) : ViewModel() {


    val originCountries: LiveData<List<String>> = repository.getTeaOriginCountries()
    val teaTypes: LiveData<List<String>> = repository.getTeaTypes()

    val time: LiveData<List<Long>> = repository.getsteeptime()


}
