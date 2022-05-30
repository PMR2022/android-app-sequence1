package fr.ec.sequence1.ui.model

sealed class ListItem {
    data class Header(
        val imageRes: Int,
        val title: String,
        val subTitle: String,
        val description : String
    ) : ListItem()

    data class Item(
        val imageRes: Int,
        val title: String,
        val subTitle: String
    ) : ListItem()
}