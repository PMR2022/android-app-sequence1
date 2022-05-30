package fr.ec.sequence1.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import fr.ec.sequence1.R
import fr.ec.sequence1.data.DataProvider
import fr.ec.sequence1.ui.adapter.ItemAdapter
import fr.ec.sequence1.ui.model.ListItem

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerview()
        loadPosts()
    }

    private fun setupRecyclerview() {
        adapter = ItemAdapter(dataSet = mutableListOf<ListItem>())
        val list = findViewById<RecyclerView>(R.id.list)
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(this, VERTICAL, false)
    }

    private fun loadPosts() {

        Thread {
            val posts = DataProvider.getPosts()

            val items = posts.map { postResponse ->
                ListItem.Item(
                    imageRes = 0,
                    title = postResponse.name,
                    subTitle = ""
                )
            }
            Log.d("MainActivity", "Product hunt response: $posts")
            this.runOnUiThread{
                adapter.display(items)
            }
        }.start()
    }
}