package com.google.developers.teacup.ui.timer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.developers.teacup.R
import kotlinx.android.synthetic.main.activity_timer.*
import kotlinx.android.synthetic.main.content_timer.*

/**
 * Activity that displays the steep timer.
 */
class TimerActivity : AppCompatActivity() {

    lateinit var timerViewModel: TimerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        start.setOnClickListener {
            reset.isEnabled = true
            timerViewModel.start()
        }

        reset.setOnClickListener { view ->
            view.isEnabled = false
            start.isEnabled = true
            timerViewModel.reset()
        }
    }
}