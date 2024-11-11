package MVVM.Model

import com.google.gson.annotations.SerializedName

data class Dimensions(
    @SerializedName("width") val width: Double ,
    @SerializedName("height") val height: Double ,
    @SerializedName("depth") val depth: Double
)
