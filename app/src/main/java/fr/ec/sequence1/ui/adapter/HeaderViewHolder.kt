package fr.ec.sequence1.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.ec.sequence1.R
import fr.ec.sequence1.ui.model.ListItem

class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val image = itemView.findViewById<ImageView>(R.id.image)
    private val title = itemView.findViewById<TextView>(R.id.title)
    private val subtitle = itemView.findViewById<TextView>(R.id.subtitle)
    private val description = itemView.findViewById<TextView>(R.id.description)

    fun bind(item: ListItem.Header) {
        title.text = item.title
        subtitle.text = item.subTitle
        image.setImageResource(item.imageRes)
        description.text = item.description
    }

}