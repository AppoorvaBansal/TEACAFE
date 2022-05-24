package com.google.developers.teacup.ui.detail

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.developers.teacup.data.DataRepository
import java.lang.reflect.InvocationTargetException

/**
 * Factory for creating a ViewModel
 */
class TeaDetailViewModelFactory(
    private val repository: DataRepository?,
    private val teaName: String
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        try {
            return modelClass.getConstructor(DataRepository::class.java, String::class.java)
                    .newInstance(repository, teaName)
        } catch (e: InstantiationException) {
            throw RuntimeException("Cannot create an instance of $modelClass", e)
        } catch (e: IllegalAccessException) {
            throw RuntimeException("Cannot create an instance of $modelClass", e)
        } catch (e: NoSuchMethodException) {
            throw RuntimeException("Cannot create an instance of $modelClass", e)
        } catch (e: InvocationTargetException) {
            throw RuntimeException("Cannot create an instance of $modelClass", e)
        }
    }

    companion object {

        fun createFactory(activity: Activity, name: String): TeaDetailViewModelFactory {
            val context = activity.applicationContext
                    ?: throw IllegalStateException("Not yet attached to Application")
            return TeaDetailViewModelFactory(DataRepository.getInstance(context), name)
        }
    }
}
