package id.ac.unpas.agenda.persistences

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import id.ac.unpas.agenda.models.Wishlist

@Dao
interface WishlistDao {

    @Query("select * from wishlist")
    fun loadAll(): LiveData<List<Wishlist>>

    @Query("select * from wishlist where id = :id")
    fun load(id: String): LiveData<Wishlist>

    @Query("select * from wishlist where id = :id")
    suspend fun getById(id: String): Wishlist?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(vararg items: Wishlist)

    @Query("delete from wishlist where id = :id")
    suspend fun delete(id: String)

    @Delete
    suspend fun delete(item: Wishlist)

    @Update
    suspend fun update(wishlist: Wishlist)
}