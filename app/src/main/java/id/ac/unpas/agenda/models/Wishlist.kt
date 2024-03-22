package id.ac.unpas.agenda.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Wishlist(
    @PrimaryKey
    val id: String,
    val itemName: String,
    val description: String,
    val category: String,
    val price: Double,
    val status: String
)
