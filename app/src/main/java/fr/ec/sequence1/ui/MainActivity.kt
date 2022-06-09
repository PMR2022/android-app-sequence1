package fr.ec.sequence1.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import fr.ec.sequence1.R
import fr.ec.sequence1.data.DataProvider
import fr.ec.sequence1.data.Post
import fr.ec.sequence1.ui.adapter.ItemAdapter
import fr.ec.sequence1.ui.model.ListItem
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ItemAdapter
    private lateinit var loader: ProgressBar
    private val dataProvider: DataProvider by lazy { DataProvider(this.application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerview()
        loader = findViewById<ProgressBar>(R.id.loader)
        loadPosts()
    }

    private fun setupRecyclerview() {
        adapter = ItemAdapter(dataSet = mutableListOf<ListItem>())
        val list = findViewById<RecyclerView>(R.id.list)
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(this, VERTICAL, false)
    }

    private val mainActivityScope = CoroutineScope(
        SupervisorJob() + Dispatchers.Main
    )

    override fun onDestroy() {
        super.onDestroy()
        mainActivityScope.cancel()
    }

    private fun loadPosts() {

        mainActivityScope.launch {
            loader.visibility = View.VISIBLE

            runCatching {
                getPosts()
            }.fold(
                onSuccess = { items ->
                    adapter.display(items)
                    loader.visibility = View.GONE
                },
                onFailure = {
                    loader.visibility = View.GONE
                    // displayErrorScreen
                    Log.e("MainActivity", "Fails -> $it")
                }
            )

        }
    }

    private suspend fun getPosts(): List<ListItem.Item> {
        val posts = dataProvider.getPosts()
        return withContext(Dispatchers.Default) {
            posts.map { post ->
                ListItem.Item(
                    imageUrl = post.imageUrl,
                    title = post.title,
                    subTitle = post.subtitle
                )
            }
        }
    }
}