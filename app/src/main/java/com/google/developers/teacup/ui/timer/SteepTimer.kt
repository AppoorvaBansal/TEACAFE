package com.google.developers.teacup.ui.timer

import android.os.SystemClock
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

/**
 * Schedule a timer count down in milliseconds.
 */
abstract class SteepTimer(private val futureTime: Long) {

    private val executors: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()
    private var timeInFuture: Long = 0
    private var schedule: ScheduledFuture<*>? = null

    /**
     * Runnable to handle the count down.
     */
    private fun run() {
        val timeLeft = timeInFuture - SystemClock.elapsedRealtime()
        if (timeLeft <= 0) {
            reset()
        } else {
            onTick(timeLeft)
        }
    }

    /**
     * Start the timer count down until the future time.
     */
    fun start() {
        timeInFuture = SystemClock.elapsedRealtime() + futureTime
        if (schedule == null) {
            schedule = executors.scheduleAtFixedRate(this::run, 0, 1000, TimeUnit.MILLISECONDS)
        }
    }

    /**
     * Rest the schedule count down time.
     */
    fun reset() {
        if (schedule != null) {
            schedule!!.cancel(false)
            schedule = null
            onTick(futureTime)
        }
    }

    /**
     * Called on every 1000 milliseconds with milliseconds left until the future time.
     *
     * @param millisUntilFinished count down to future time in milliseconds.
     */
    abstract fun onTick(millisUntilFinished: Long)
}
