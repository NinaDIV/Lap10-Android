package MVVM.Model

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("products") val products: List<ProductModel> ,
    @SerializedName("total") val total: Int ,
    @SerializedName("skip") val skip: Int ,
    @SerializedName("limit") val limit: Int
)
