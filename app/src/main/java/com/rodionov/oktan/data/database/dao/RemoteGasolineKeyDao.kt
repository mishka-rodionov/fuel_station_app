package com.rodionov.oktan.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rodionov.oktan.data.database.dto.RemoteGasolineKey
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
interface RemoteGasolineKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(remoteKey: List<RemoteGasolineKey>)

    @Query("SELECT * FROM remote_gasoline_keys WHERE id = :id")
    fun getRemoteKey(id: String): RemoteGasolineKey?

    @Query("DELETE FROM remote_gasoline_keys")
    fun clearRemoteKeys()

}