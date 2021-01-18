package com.rodionov.oktan.data.database.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_gasoline_keys")
data class RemoteGasolineKey(
        @PrimaryKey
        @ColumnInfo(name = "id")
        val gasolineStationId: String,
        val prevKey: Int?,
        val nextKey: Int?
) {
}