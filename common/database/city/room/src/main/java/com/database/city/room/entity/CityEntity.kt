package com.database.city.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.database.city.room.entity.CityEntity.Companion.TABLE

@Entity(tableName = TABLE)
data class CityEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val id: Int? = null,
    @ColumnInfo(name = CITY)
    val city: String,
    @ColumnInfo(name = X)
    val x: Double,
    @ColumnInfo(name = Y)
    val y: Double,
) {
    companion object {
        const val TABLE = "city_table"
        const val ID = "id"
        const val CITY = "city"
        const val X = "x"
        const val Y = "y"
    }
}
