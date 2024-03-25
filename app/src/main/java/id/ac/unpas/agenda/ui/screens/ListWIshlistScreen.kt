package id.ac.unpas.agenda.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import id.ac.unpas.agenda.models.Wishlist
import id.ac.unpas.agenda.persistences.WishlistDao

@Composable
fun ListWishlistScreen(wishlistDao: WishlistDao, onEditItem: (Wishlist) -> Unit) {
    val wishlistItems: LiveData<List<Wishlist>> = wishlistDao.loadAll()
    val items: List<Wishlist> by wishlistItems.observeAsState(initial = listOf())

    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            items(items.size) { index ->
                // For each item, WishlistItem now includes onEditClick and onDeleteClick
                Card(modifier = Modifier.padding(4.dp)) {
                    WishlistItem(
                        item = items[index],
                        onEditClick = { onEditItem(items[index]) },
                        onDeleteClick = {
                            // Placeholder for delete action, replace with actual logic
                            Log.d("Wishlist", "Delete clicked for item: ${items[index].itemName}")
                        }
                    )
                }
            }
        }
    }
}
