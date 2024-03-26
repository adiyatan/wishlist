package id.ac.unpas.agenda.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import id.ac.unpas.agenda.models.Wishlist
import id.ac.unpas.agenda.persistences.WishlistDao
import kotlinx.coroutines.launch

@Composable
fun WishlistEditDialog(item: Wishlist, onDismiss: () -> Unit, wishlistDao: WishlistDao) {
    val coroutineScope = rememberCoroutineScope()
    val (itemName, setItemName) = remember { mutableStateOf(item.itemName) }
    val (description, setDescription) = remember { mutableStateOf(item.description) }
    val (category, setCategory) = remember { mutableStateOf(item.category) }
    val (price, setPrice) = remember { mutableStateOf(item.price.toString()) }
    val (status, setStatus) = remember { mutableStateOf(item.status) }

    // State to hold validation error message
    val (errorMessage, setErrorMessage) = remember { mutableStateOf<String?>(null) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Wishlist Item") },
        text = {
            Column {
                TextField(value = itemName, onValueChange = setItemName, label = { Text("Nama Barang") })
                TextField(value = description, onValueChange = setDescription, label = { Text("Deskripsi") })
                TextField(value = category, onValueChange = setCategory, label = { Text("Kategori") })
                TextField(
                    value = price,
                    onValueChange = setPrice,
                    label = { Text("Harga") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
                TextField(value = status, onValueChange = setStatus, label = { Text("Link pembelian") })

                // Display error message if any
                if (errorMessage != null) {
                    Text(errorMessage, color = MaterialTheme.colorScheme.error)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                if (itemName.isBlank() || description.isBlank() || category.isBlank() || price.isBlank() || status.isBlank() || price.toDoubleOrNull() == null) {
                    setErrorMessage("Please fill all fields correctly.")
                } else {
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
