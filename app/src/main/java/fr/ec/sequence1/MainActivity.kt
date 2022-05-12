package fr.ec.sequence1

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = findViewById<RecyclerView>(R.id.list)
        list.adapter = ItemAdapter(dataSet = provideDataSet())
        list.layoutManager = LinearLayoutManager(this, VERTICAL, false)

    }


    class ItemAdapter(
        private val dataSet: List<Item>
    ) : RecyclerView.Adapter<ItemViewHolder>() {

        override fun getItemCount(): Int = dataSet.size

        override fun getItemViewType(position: Int): Int {
            return if(position == 0)HEADER_ITEM_ID else ITEM_ID
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val itemView =
                if(viewType == HEADER_ITEM_ID) {
                    LayoutInflater.from(parent.context).inflate(R.layout.item_header_layout, parent, false)

                }else {
                    LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)

                }

            return ItemViewHolder(itemView = itemView)
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.bind(item = dataSet[position])
        }


    }

    companion object {
        const val HEADER_ITEM_ID = 0
        const val ITEM_ID = 1
    }

    fun provideDataSet(): List<Item> {
        val result = mutableListOf<Item>()
        repeat(1_000) { intex ->
            val item = Item(
                imageRes = R.mipmap.ic_launcher,
                title = "Titre $intex",
                subTitle = "Sous-Titre $intex",
            )

            result.add(item)
        }
        return result
    }

    data class Item(
        val imageRes: Int,
        val title: String,
        val subTitle: String
    )

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val image = itemView.findViewById<ImageView>(R.id.image)
        private val title = itemView.findViewById<TextView>(R.id.title)
        private val subtitle = itemView.findViewById<TextView>(R.id.subtitle)

        fun bind(item: Item) {
            title.text = item.title
            subtitle.text = item.subTitle
            image.setImageResource(item.imageRes)
        }

    }
}