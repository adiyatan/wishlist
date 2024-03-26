package id.ac.unpas.agenda.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.benasher44.uuid.uuid4
import id.ac.unpas.agenda.models.Wishlist
import id.ac.unpas.agenda.persistences.WishlistDao
import kotlinx.coroutines.launch

@Composable
fun FormWishlistScreen(wishlistDao: WishlistDao) {
    val scope = rememberCoroutineScope()

    // State for holding form data
    val itemName = remember { mutableStateOf(TextFieldValue("")) }
    val description = remember { mutableStateOf(TextFieldValue("")) }
    val category = remember { mutableStateOf(TextFieldValue("")) }
    val price = remember { mutableStateOf(TextFieldValue("")) }
    val status = remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier.padding(10.dp)) {
        OutlinedTextField(
            label = { Text(text = "Nama Barang") },
            modifier = Modifier.fillMaxWidth(),
            value = itemName.value,
            onValueChange = {
                itemName.value = it
            }
        )

        OutlinedTextField(
            label = { Text(text = "Deskripsi") },
            modifier = Modifier.fillMaxWidth(),
            value = description.value,
            onValueChange = {
                description.value = it
            }
        )

        OutlinedTextField(
            label = { Text(text = "Kategori") },
            modifier = Modifier.fillMaxWidth(),
            value = category.value,
            onValueChange = {
                category.value = it
            }
        )

        OutlinedTextField(
            label = { Text(text = "Harga") },
            modifier = Modifier.fillMaxWidth(),
            value = price.value,
            onValueChange = {
                price.value = it
            }
        )

        OutlinedTextField(
            label = { Text(text = "Link pembelian") },
            modifier = Modifier.fillMaxWidth(),
            value = status.value,
            onValueChange = {
                status.value = it
            }
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val item = Wishlist(
                    id = uuid4().toString(),
                    itemName = itemName.value.text,
                    description = description.value.text,
                    category = category.value.text,
                    price = price.value.text.toDoubleOrNull() ?: 0.0,
                    status = status.value.text
                )
                scope.launch {
                    wishlistDao.upsert(item)
                }
                // Clear form fields after saving
                itemName.value = TextFieldValue("")
                description.value = TextFieldValue("")
                category.value = TextFieldValue("")
                price.value = TextFieldValue("")
                status.value = TextFieldValue("")
            }
        ) {
            Text(text = "Simpan")
        }
    }
}
