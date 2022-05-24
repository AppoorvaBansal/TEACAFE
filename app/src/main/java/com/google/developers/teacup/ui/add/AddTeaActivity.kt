package com.google.developers.teacup.ui.add

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.developers.teacup.R
import kotlinx.android.synthetic.main.activity_add_tea.*

/**
 * Save a Tea to local data source.
 */
class AddTeaActivity : AppCompatActivity() {

    private lateinit var addTeaViewModel: AddTeaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_tea)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val teaViewModelFactory = AddTeaViewModelFactory.createFactory(this)
        addTeaViewModel =
            ViewModelProvider(this, teaViewModelFactory).get(AddTeaViewModel::class.java)

        addTeaViewModel.isSaved.observe(this, Observer<Boolean> { this.hasSaved(it.or(false)) })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_save, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_save) {
            saveTea()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Save all fields.
     */
    private fun saveTea() {
        val teaTypes = resources.getStringArray(R.array.tea_types)
        val type = teaTypes[tea_type.selectedItemId.toInt()]
        val name = tea_name.text?.toString()?.trim { it <= ' ' } ?: ""
        val description = description.text?.toString() ?: ""
        val origin = origin.text?.toString() ?: ""
        val ingredients = ingredients.text?.toString() ?: ""
        val steepTime = steep.text?.toString() ?: ""
        val caffeine = caffeine.text?.toString() ?: ""

        addTeaViewModel.save(name, type, origin, steepTime, description, ingredients, caffeine)
    }

    /**
     * If input has no errors and Tea is saved close activity.
     *
     * @param saved if TRUE Tea is saved and activity should be closed if FALSE
     * show an error.
     */
    private fun hasSaved(saved: Boolean) {
        if (!saved) {
            tea_name.error = getString(R.string.required_text_tea_name)
            return
        }
        finish()
    }
}
