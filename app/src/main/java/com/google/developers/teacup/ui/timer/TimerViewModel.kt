package com.google.developers.teacup.ui.timer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel for steep timer activity.
 */
class TimerViewModel : ViewModel() {

    private val lapseTime = MutableLiveData<Long>()
    private var steepTimer: SteepTimer? = null
    val STEEP_TIME_MILLIS: Long = 240000

    val timer: LiveData<Long>
        get() = lapseTime

    /**
     * Set how long the steep timer should run.
     */
    fun setSteepTimer() {
        if (steepTimer == null) {
            lapseTime.postValue(STEEP_TIME_MILLIS)
            steepTimer = object : SteepTimer(STEEP_TIME_MILLIS) {
                override fun onTick(millisUntilFinished: Long) {
                    lapseTime.postValue(millisUntilFinished)
                }
            }
        }
    }

    /**
     * Trigger timer to start.
     */
    fun start() {
        if (steepTimer != null) {
            steepTimer!!.start()
        }
    }

    /**
     * Reset timer to the beginning.
     */
    fun reset() {
        if (steepTimer != null) {
            steepTimer!!.reset()
        }
    }
}
