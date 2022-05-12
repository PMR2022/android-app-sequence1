package fr.ec.sequence1.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import fr.ec.sequence1.R
import fr.ec.sequence1.ui.adapter.ItemAdapter
import fr.ec.sequence1.ui.model.Item

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = findViewById<RecyclerView>(R.id.list)
        list.adapter = ItemAdapter(dataSet = provideDataSet())
        list.layoutManager = LinearLayoutManager(this, VERTICAL, false)

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

}