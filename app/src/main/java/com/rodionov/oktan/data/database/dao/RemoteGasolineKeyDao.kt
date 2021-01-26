package com.rodionov.oktan.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rodionov.oktan.data.database.dto.RemoteGasolineKeyEntity

@Dao
interface RemoteGasolineKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(remoteKeyEntity: List<RemoteGasolineKeyEntity>)

    @Query("SELECT * FROM remote_gasoline_keys WHERE id = :id")
    fun getRemoteKey(id: String): RemoteGasolineKeyEntity?

    @Query("DELETE FROM remote_gasoline_keys")
    fun clearRemoteKeys()

}