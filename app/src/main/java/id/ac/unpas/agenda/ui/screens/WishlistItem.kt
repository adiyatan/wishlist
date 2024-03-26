package id.ac.unpas.agenda.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import id.ac.unpas.agenda.models.Wishlist
import androidx.compose.material3.Text
/**
 * Displays a single Wishlist item inside a card, with options to edit and delete.
 */
@Composable
fun WishlistItem(item: Wishlist, onEditClick: () -> Unit, onDeleteClick: () -> Unit) {
    val context = LocalContext.current // Mendapatkan context saat ini

    Card(modifier = Modifier.padding(8.dp), elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(modifier = Modifier.padding(bottom = 8.dp)) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = item.itemName,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = item.category,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodySmall
            )
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Text(
                    text = "${item.price}",
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {
                    val uri = Uri.parse(item.status)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    context.startActivity(intent)
                }) {
                    Text("Buy")
                }
                IconButton(onClick = onEditClick) {
                    Icon(Icons.Filled.Edit, contentDescription = "Edit")
                }
                IconButton(onClick = onDeleteClick) {
                    Icon(Icons.Filled.Delete, contentDescription = "Delete")
                }
            }
        }
    }
}

