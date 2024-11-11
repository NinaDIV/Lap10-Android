package MVVM.MODEL

import com.google.gson.annotations.SerializedName


data class Amphibian(
    @SerializedName("name") val name: String, // Cambié de "nombre" a "name"
    @SerializedName("type") val type: String, // Cambié de "tipo" a "type"
    @SerializedName("description") val description: String, // Cambié de "descripcion" a "description"
    @SerializedName("img_src") val img_src: String // Cambié de "img_src" a "img_src"
)