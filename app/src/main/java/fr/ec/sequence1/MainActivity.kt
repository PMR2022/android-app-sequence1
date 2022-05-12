package fr.ec.sequence1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = findViewById<LinearLayout>(R.id.list)

        repeat(10) { index ->
            val item = layoutInflater.inflate(R.layout.item_layout,list,false)
            val title = item.findViewById<TextView>(R.id.title)
            val subtitle = item.findViewById<TextView>(R.id.subtitle)

            title.text = "Titre $index"
            subtitle.text = "Sous-titre $index"
            list.addView(item)

        }

    }
}