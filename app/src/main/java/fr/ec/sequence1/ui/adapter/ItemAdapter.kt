package fr.ec.sequence1.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.ec.sequence1.R
import fr.ec.sequence1.ui.model.Item

class ItemAdapter(
    private val dataSet: List<Item>
) : RecyclerView.Adapter<ItemViewHolder>() {

    override fun getItemCount(): Int = dataSet.size

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) HEADER_ITEM_ID else ITEM_ID
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView =
            if (viewType == HEADER_ITEM_ID) {
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_header_layout, parent, false)

            } else {
                LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)

            }

        return ItemViewHolder(itemView = itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(item = dataSet[position])
    }

    companion object {
        const val HEADER_ITEM_ID = 0
        const val ITEM_ID = 1
    }


}