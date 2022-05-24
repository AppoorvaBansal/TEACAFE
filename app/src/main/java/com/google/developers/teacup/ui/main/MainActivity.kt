package com.google.developers.teacup.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.google.developers.teacup.R
import com.google.developers.teacup.databinding.ActivityMainBinding
import com.google.developers.teacup.ui.SettingActivity
import com.google.developers.teacup.ui.add.AddTeaActivity
import com.google.developers.teacup.ui.list.TeaListActivity
import com.google.developers.teacup.ui.timer.TimerActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TeaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Set the content view of the Activity to be the root view of the layout
        setContentView(binding.root)
        val teaViewModelFactory = TeaViewModelFactory.createFactory(this)
        viewModel = ViewModelProvider(this,teaViewModelFactory).get(TeaViewModel::class.java)


        var teaType:LiveData<List<String>>
        teaType=viewModel.teaTypes
        val time: LiveData<List<Long>> = viewModel.time
        Log.v("check","tea: "+teaType.toString())

        //val length : Int =teaName.maxBy { it.length}.length

        val teaName: List<String>? = teaType.value
        val teaOrigin: List<String>? = viewModel.originCountries.value
        val steepTime: List<Long>? = time.value

        Log.v("check","time: "+steepTime.toString())

        // addRadioButtons(teaType)
        //viewModel.isSaved.observe(this, Observer<Boolean> { this.hasSaved(it.or(false)) })
        val selectedId = binding.rdteaOrigin.checkedRadioButtonId
        val teacheck = when (selectedId) {
            R.id.option_black_tea -> "Black Tea"
            R.id.option_Herbal_Tea -> "Herbal Tea"
            else -> "Green Tea"
        }


        go_to_Result.setOnClickListener {
           val i= Intent(this,TeaListActivity::class.java)
            startActivity(i)


       }

       // val adapter = TeaPickerViewAdapter()
        //pager.adapter = adapter

    }


    fun addRadioButtons(number: LiveData<List<String>>,) {
        rdtea_origin.setOrientation(LinearLayout.VERTICAL)
        val length=10
        for (i in 1..length) {
            val rdbtn = RadioButton(this)
            rdbtn.id = View.generateViewId()
            rdbtn.text =""
            //rdbtn.setOnClickListener(this)
           //rdtea_origin.addView(rdbtn)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent: Intent? = when (item.itemId) {
            R.id.action_add -> Intent(this, AddTeaActivity::class.java)
            R.id.action_timer -> Intent(this, TimerActivity::class.java)
            R.id.action_list -> Intent(this, TeaListActivity::class.java)
            R.id.action_settings -> Intent(this, SettingActivity::class.java)
            else -> null
        } ?: return super.onOptionsItemSelected(item)

        startActivity(intent)
        return true
    }
}




