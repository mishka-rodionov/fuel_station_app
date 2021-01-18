package com.rodionov.oktan.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rodionov.oktan.data.database.dto.RemoteGasolineKeyDto

@Dao
interface RemoteGasolineKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(remoteKeyDto: List<RemoteGasolineKeyDto>)

    @Query("SELECT * FROM remote_gasoline_keys WHERE id = :id")
    fun getRemoteKey(id: String): RemoteGasolineKeyDto?

    @Query("DELETE FROM remote_gasoline_keys")
    fun clearRemoteKeys()

}