package com.google.developers.teacup.ui.main

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.developers.teacup.data.DataRepository
import java.lang.reflect.InvocationTargetException

class TeaViewModelFactory(private val repository: DataRepository?) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        try {
            return modelClass.getConstructor(DataRepository::class.java)
                    .newInstance(repository)
        } catch (e: Throwable) {
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

        fun createFactory(activity: Activity): TeaViewModelFactory {
            val context = activity.applicationContext
                    ?: throw IllegalStateException("Not yet attached to Application")

            return TeaViewModelFactory(DataRepository.getInstance(context))
        }
    }
}
