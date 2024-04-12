package com.database.city.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.database.city.room.entity.CityEntity.Companion.TABLE

@Entity(tableName = TABLE)
data class CityEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val id: Long,
    @ColumnInfo(name = CITY)
    val city: String
) {
    companion object {
        const val TABLE = "city"
        const val ID = "id"
        const val CITY = "city"
    }
}
