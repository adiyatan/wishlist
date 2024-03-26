package id.ac.unpas.agenda.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.benasher44.uuid.uuid4
import id.ac.unpas.agenda.models.Wishlist
import id.ac.unpas.agenda.persistences.WishlistDao
import kotlinx.coroutines.launch

@Composable
fun FormWishlistScreen(wishlistDao: WishlistDao) {
    val scope = rememberCoroutineScope()

    // State for holding form data as Strings
    var itemName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }
    val priceError = remember { mutableStateOf("") }
    val categories = listOf("Pokok", "Sekunder")
    var expanded by remember { mutableStateOf(false) }

    // Check if all fields are filled
    val isFormValid = itemName.isNotBlank() && description.isNotBlank() && category.isNotBlank() && price.isNotBlank() && status.isNotBlank()

    Column(modifier = Modifier.padding(10.dp)) {
        OutlinedTextField(
            label = { Text("Nama Barang") },
            value = itemName,
            onValueChange = { itemName = it },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            label = { Text("Deskripsi") },
            value = description,
            onValueChange = { description = it },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            label = { Text("Kategori") },
            value = category,
            onValueChange = { category = it },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            label = { Text(text = "Harga") },
            modifier = Modifier.fillMaxWidth(),
            value = price,
            onValueChange = { price = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = priceError.value.isNotEmpty()
        )
        if (priceError.value.isNotEmpty()) {
            Text(text = priceError.value, color = androidx.compose.ui.graphics.Color.Red)
        }

        OutlinedTextField(
            label = { Text("Link Pembelian") },
            value = status,
            onValueChange = { status = it },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (isFormValid) {
                    val newItem = Wishlist(
                        id = uuid4().toString(),
                        itemName = itemName,
                        description = description,
                        category = category,
                        price = price.toDoubleOrNull() ?: 0.0,
                        status = status
                    )
                    scope.launch {
                        wishlistDao.upsert(newItem)
                    }

                    // Clear the form
                    itemName = ""
                    description = ""
                    category = ""
                    price = ""
                    status = ""
                    priceError.value = ""
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = isFormValid // Button is only clickable if the form is valid
        ) {
            Text("Simpan")
        }
    }
}
