package com.google.developers.teacup.picker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.developers.teacup.R

class TeaPickerViewAdapter : RecyclerView.Adapter<TeaPickerViewAdapter.CriteriaViewHolder>() {

    private val viewData = LinkedHashMap<PageType, List<String>>()
    private var selectedCountry = ""
    private var selectedTeaType = ""

    fun submitData(key: PageType, list: List<String>) {
        viewData[key] = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CriteriaViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.pager_item, parent, false)
        return CriteriaViewHolder(view)
    }

    override fun onBindViewHolder(holder: CriteriaViewHolder, position: Int) {
        val key = getIndexKey(position) ?: return
        val pageData = viewData[key] ?: return
    }

    override fun getItemCount(): Int {
        return viewData.size
    }

    private fun getIndexKey(position: Int): PageType? {
        return viewData.keys.toTypedArray().getOrNull(position)
    }

    enum class PageType {
        COUNTRIES, TYPES
    }

    // stores and recycles views as they are scrolled off screen
    inner class CriteriaViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
    }
}
