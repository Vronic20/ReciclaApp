package upc.pe.recycleappupc.dao

import androidx.room.*
import upc.pe.recycleappupc.models.AlarmRoom

@Dao
interface AlarmDAO {

    @Query("SELECT * FROM AlarmRoom")
    fun getAll(): List<AlarmRoom>

    @Query("SELECT * FROM AlarmRoom WHERE id=:id")
    fun getById(id: Int): AlarmRoom

    @Insert
    fun insert(alarm: AlarmRoom): Long

    @Update
    fun update(alarm: AlarmRoom)

}