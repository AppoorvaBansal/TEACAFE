package com.google.developers.teacup.paging

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.developers.teacup.data.Tea

/**
 * A RecyclerView ViewHolder that displays a Tea.
 */
class TeaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val context = itemView.context
    private lateinit var tea: Tea

    /**
     * Attach values to views.
     */
    fun bindTo(tea: Tea, clickListener: (Tea) -> Unit) {

    }

    fun getTea(): Tea = tea
}
