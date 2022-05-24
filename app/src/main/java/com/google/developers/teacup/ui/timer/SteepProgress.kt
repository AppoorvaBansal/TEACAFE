package com.google.developers.teacup.ui.timer

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.TextPaint
import android.util.AttributeSet
import android.widget.ProgressBar
import com.google.developers.teacup.R
import java.util.Locale
import java.util.concurrent.TimeUnit

/**
 * View that displays minutes in the center of a circular progress bar.
 */
class SteepProgress(context: Context, attrs: AttributeSet) :
    ProgressBar(context, attrs, R.attr.progressBarStyle, R.style.SteepRing) {

    private val textPaint: TextPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)
    /**
     * Get current view time text.
     */
    var time = "-:--"
        private set
    private var maxSteepTime: Long? = 0L
    private val maxProgress = 300
    private val fontHeight: Int
    private var positionX: Int = 0
    private var positionY: Int = 0
    private val gestureThresholdDp = 76

    init {
        textPaint.density = resources.displayMetrics.density
        textPaint.textSize = gestureThresholdDp * resources.displayMetrics.scaledDensity
        textPaint.color = Color.WHITE
        textPaint.isAntiAlias = true
        textPaint.style = Paint.Style.FILL
        textPaint.textAlign = Paint.Align.CENTER

        val fontMetrics = textPaint.fontMetrics
        fontHeight = (fontMetrics.descent + fontMetrics.ascent).toInt()
        max = maxProgress
    }

    @Synchronized
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        positionX = measuredWidth / 2
        positionY = measuredHeight / 2 - fontHeight / 2
    }

    @Synchronized
    override fun onDraw(canvas: Canvas) {
        canvas.drawText(time, positionX.toFloat(), positionY.toFloat(), textPaint)
        super.onDraw(canvas)
    }

    /**
     * Set max time steep required to calculate the progress.
     *
     * @param time the max time in milliseconds
     */
    fun setMaxTime(time: Long) {
        maxSteepTime = TimeUnit.MILLISECONDS.toSeconds(time)
        setTime(maxSteepTime!!)
    }

    /**
     * Update time text and progress bar position.
     *
     * @param milliseconds unit time in Milliseconds
     */
    @Synchronized
    fun setTime(milliseconds: Long) {
        val minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds) % 60
        val totalSeconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds)
        val seconds = totalSeconds % 60

        time = if (minutes == 0L) {
            String.format(Locale.ROOT, "%02d", seconds)
        } else {
            String.format(Locale.ROOT, "%1d:%02d", minutes, seconds)
        }

        val progress = maxProgress - totalSeconds * maxProgress / maxSteepTime!!
        setProgress(progress.toInt())
    }
}
