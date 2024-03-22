package id.ac.unpas.agenda.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.agenda.models.Wishlist


@Database(entities = [Wishlist::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun WishlistDao(): WishlistDao

    companion object {
        const val DATABASE_NAME = "Wishlist"
    }
}