package com.google.developers.teacup.paging

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.developers.teacup.data.Tea
import com.google.developers.teacup.databinding.ListItemBinding
import java.text.SimpleDateFormat
import java.util.*

/**
 * Implementation of an Paging adapter that shows list of Teas.
 */
class TeaAdapter(
    private val clickListener: (Tea) -> Unit
) : PagedListAdapter<Tea, TeaAdapter.TeaViewHolder>(DIFF_CALLBACK) {

    class TeaViewHolder( private var binding: ListItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(tea: Tea) {
            binding.txtTeaItemTitle.text = tea.name
            binding.txtTeaTime.text = SimpleDateFormat(
                "h:mm a").format(
                Date(tea.steepTimeMs.toLong() * 1000)
            )
            binding.txtTeaItemCounrty.text=tea.origin
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeaViewHolder {
        val viewHolder = TeaViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from( parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            getItem(position)?.let { it1 -> clickListener(it1) }
        }
        return viewHolder

    }



    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Tea>() {
            override fun areItemsTheSame(oldItem: Tea, newItem: Tea): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Tea, newItem: Tea): Boolean {
                return oldItem == newItem
            }
        }
    }


    override fun onBindViewHolder(holder: TeaViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}
