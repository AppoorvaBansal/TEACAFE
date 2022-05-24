package com.google.developers.teacup.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.developers.teacup.data.DataRepository
import com.google.developers.teacup.data.Tea
import java.util.concurrent.TimeUnit

class AddTeaViewModel(private val repository: DataRepository) : ViewModel() {
    private val teaWasSaved = MutableLiveData<Boolean>()

    val isSaved: LiveData<Boolean>
        get() = teaWasSaved

    private fun save(tea: Tea) {
        repository.insert(tea)
    }

    fun save(
        name: String,
        type: String,
        origin: String,
        steepTime: String,
        description: String,
        ingredients: String,
        caffeine: String
    ) {
        if (name.isEmpty()) {
            teaWasSaved.value = false
            return
        }
        var steepMs: Long = steepTime.parseLong(steepTime)
        steepMs = TimeUnit.MINUTES.toMillis(steepMs)
        val tea = Tea(
            name = name,
            type = type,
            origin = origin,
            steepTimeMs = steepMs,
            description = description,
            ingredients = ingredients,
            caffeineLevel = caffeine
        )
        save(tea)
        teaWasSaved.value = true
    }

    /**
     * If string does not have integers then return 0.
     *
     * @param string to parse are int
     * @return a parse int or default value 0
     */
    fun String.parseLong(string: String): Long {
        return if (string.matches("\\d+".toRegex())) java.lang.Long.parseLong(string) else 0L
    }
}
