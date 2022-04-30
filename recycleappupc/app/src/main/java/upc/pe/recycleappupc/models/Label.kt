package upc.pe.recycleappupc.models

import com.google.gson.annotations.SerializedName

class Label (
    @SerializedName("name")
    val name: String,

    @SerializedName("confidence")
    val confidence: Double,
)
