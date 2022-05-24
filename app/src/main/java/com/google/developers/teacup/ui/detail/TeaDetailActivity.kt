package com.google.developers.teacup.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.developers.teacup.R
import com.google.developers.teacup.data.Tea
import java.util.concurrent.TimeUnit
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*

/**
 * Activity that displays all the information about a particular Tea
 */
class TeaDetailActivity : AppCompatActivity() {
    private lateinit var teaDetailViewModel: TeaDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val teaName = intent.getStringExtra(TEA_NAME)!!
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val viewModelFactory = TeaDetailViewModelFactory
            .createFactory(this, teaName)
        teaDetailViewModel = ViewModelProvider(this, viewModelFactory)
            .get(TeaDetailViewModel::class.java)

        teaDetailViewModel.teaImage.observe(this, Observer { teaDetail ->
            tea_image.setImageDrawable(resources.getDrawable(teaDetail.imageId, null))
        })
        teaDetailViewModel.tea.observe(this, Observer<Tea> { this.displayTea(it) })
    }

    /**
     * Attach the Tea to the view.
     */
    private fun displayTea(tea: Tea?) {
        if (tea == null) {
            return
        }
        collapsing_toolbar.title = tea.name

        val steepMinute = tea.steepTimeMs
        teaDetailViewModel.setTeaImage(tea.type)
        // description.text = tea.description
        origin.text = tea.origin
        ingredients.text = tea.ingredients
        tea_type.text = tea.type
        steep_time.text = getString(
            R.string.steep_time_minutes,
            TimeUnit.MILLISECONDS.toMinutes(steepMinute)
        )
        caffeine_level.text = getString(R.string.caffeine, tea.caffeineLevel)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)

        val menuItem = menu.findItem(R.id.action_favorite)
        teaDetailViewModel.tea.observe(this, Observer { tea ->
            if (tea.isFavorite) {
                menuItem.icon = resources.getDrawable(R.drawable.ic_favorite, null)
            } else {
                menuItem.icon = resources.getDrawable(R.drawable.ic_favorite_border, null)
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_favorite) {
            teaDetailViewModel.setFavorite()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val TEA_NAME = "TEA_NAME"
    }
}
