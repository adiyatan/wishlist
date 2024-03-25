package id.ac.unpas.agenda.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.room.Room
import id.ac.unpas.agenda.models.Wishlist
import id.ac.unpas.agenda.persistences.AppDatabase

/**
 * Screen for displaying and interacting with the wishlist.
 */
@Composable
fun WishlistScreen() {
    val context = LocalContext.current
    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        AppDatabase.DATABASE_NAME
    ).build()

    // Obtain an instance of WishlistDao from the database.
    val wishlistDao = db.WishlistDao()

    val (itemToEdit, setItemToEdit) = remember { mutableStateOf<Wishlist?>(null) }

    if (itemToEdit != null) {
        WishlistEditDialog(item = itemToEdit!!, onDismiss = { setItemToEdit(null) }, wishlistDao = wishlistDao)
    }

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        FormWishlistScreen(wishlistDao)
        ListWishlistScreen(wishlistDao, onEditItem = { item -> setItemToEdit(item) })
    }
}
