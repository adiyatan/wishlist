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
import kotlinx.coroutines.launch

/**
 * Displays a single Wishlist item inside a card, with options to edit and delete.
 */
@Composable
fun WishlistEditDialog(item: Wishlist, onDismiss: () -> Unit, wishlistDao: WishlistDao) {
    val coroutineScope = rememberCoroutineScope()
    val (itemName, setItemName) = remember { mutableStateOf(item.itemName) }
    val (description, setDescription) = remember { mutableStateOf(item.description) }
    val (category, setCategory) = remember { mutableStateOf(item.category) }
    val (price, setPrice) = remember { mutableStateOf(item.price.toString()) }
    val (status, setStatus) = remember { mutableStateOf(item.status) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Wishlist Item") },
        text = {
            Column {
                TextField(value = itemName, onValueChange = setItemName, label = { Text("Nama Barang") })
                TextField(value = description, onValueChange = setDescription, label = { Text("Deskripsi") })
                TextField(value = category, onValueChange = setCategory, label = { Text("Kategori") })
                TextField(value = price, onValueChange = setPrice, label = { Text("Harga") })
                TextField(value = status, onValueChange = setStatus, label = { Text("Status") })
            }
        },
        confirmButton = {
            Button(onClick = {
                coroutineScope.launch {
                    wishlistDao.update(
                        item.copy(
                            itemName = itemName,
                            description = description,
                            category = category,
                            price = price.toDoubleOrNull() ?: 0.0,
                            status = status
                        )
                    )
                    onDismiss()
                }
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
