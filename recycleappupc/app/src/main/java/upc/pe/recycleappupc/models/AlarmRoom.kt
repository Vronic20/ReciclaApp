package upc.pe.recycleappupc.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
class AlarmRoom(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,

    @ColumnInfo
    var horaString: String,

    @ColumnInfo
    var hora: Int,

    @ColumnInfo
    var minuto: Int,

    @ColumnInfo
    var etiqueta: String,

    @ColumnInfo
    var isActive: Boolean,

    @ColumnInfo(defaultValue = "false")
    var isRepetitive: Boolean
)