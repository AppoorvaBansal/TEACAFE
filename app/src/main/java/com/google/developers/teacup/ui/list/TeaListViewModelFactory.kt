package com.google.developers.teacup.ui.list

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.developers.teacup.data.DataRepository
import com.google.developers.teacup.ui.detail.TeaDetailViewModelFactory
import java.lang.reflect.InvocationTargetException

class TeaListViewModelFactory(
    private val mRepository: DataRepository?,
    private val mSortBy: String?
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        try {
            return modelClass.getConstructor(DataRepository::class.java, String::class.java)
                .newInstance(mRepository, mSortBy)
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
        fun createFactory(activity: Activity, name: String): TeaListViewModelFactory {
            val context = activity.applicationContext
                ?: throw IllegalStateException("Not yet attached to Application")
            return TeaListViewModelFactory(DataRepository.getInstance(context), name)
        }

    }

}
