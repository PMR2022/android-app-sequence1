package fr.ec.sequence1.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.ec.sequence1.R
import fr.ec.sequence1.ui.model.ListItem
import java.lang.IllegalArgumentException

class ItemAdapter(
    private val dataSet: MutableList<ListItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = dataSet.size

    override fun getItemViewType(position: Int): Int {
        val listItem = dataSet[position]
        return when (listItem) {
            is ListItem.Header -> HEADER_ITEM_ID
            is ListItem.Item -> ITEM_ID
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == HEADER_ITEM_ID) {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_header_layout, parent, false)

            HeaderViewHolder(itemView = itemView)

        } else {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
            ItemViewHolder(itemView = itemView)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bind(item = dataSet[position] as ListItem.Item)
        } else if (holder is HeaderViewHolder) {
            holder.bind(item = dataSet[position] as ListItem.Header)
        } else {
            throw IllegalArgumentException()
        }

    }

    fun display(posts: List<ListItem>) {
        dataSet.addAll(posts)
        notifyDataSetChanged()
    }

    companion object {
        const val HEADER_ITEM_ID = 0
        const val ITEM_ID = 1
    }


}