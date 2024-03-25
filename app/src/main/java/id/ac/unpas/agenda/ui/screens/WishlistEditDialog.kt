package id.ac.unpas.agenda.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import id.ac.unpas.agenda.models.Wishlist
import id.ac.unpas.agenda.persistences.WishlistDao

/**
 * Displays a single Wishlist item inside a card, with options to edit and delete.
 */
@Composable
fun WishlistEditDialog(item: Wishlist, onDismiss: () -> Unit, wishlistDao: WishlistDao) {
    // Gunakan state untuk memanipulasi input dari user
    val (itemName, setItemName) = remember { mutableStateOf(item.itemName) }
    val (description, setDescription) = remember { mutableStateOf(item.description) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Wishlist Item") },
        text = {
            Column {
                TextField(value = itemName, onValueChange = setItemName, label = { Text("Nama Barang") })
                TextField(value = description, onValueChange = setDescription, label = { Text("Deskripsi") })
                // TextField untuk fields lainnya
            }
        },
        confirmButton = {
            Button(onClick = {
//                val updatedItem = item.copy(itemName = itemName, description = description) // Update fields lainnya
//                val scope = rememberCoroutineScope()
//                scope.launch { wishlistDao.update(updatedItem) }
                onDismiss()
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

