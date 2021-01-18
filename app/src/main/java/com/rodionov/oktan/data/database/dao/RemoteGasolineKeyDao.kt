package com.rodionov.oktan.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rodionov.oktan.data.database.dto.RemoteGasolineKey

@Dao
interface RemoteGasolineKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteGasolineKey>)

    @Query("SELECT * FROM remote_gasoline_keys WHERE id = :id")
    suspend fun getRemoteKey(id: String): RemoteGasolineKey?

    @Query("DELETE FROM remote_gasoline_keys")
    suspend fun clearRemoteKeys()

}