package MVVM.Model


import com.google.gson.annotations.SerializedName

data class ProductModel(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("category") val category: String,
    @SerializedName("price") val price: Double,
    @SerializedName("discountPercentage") val discountPercentage: Double,
    @SerializedName("rating") val rating: Double,
    @SerializedName("stock") val stock: Int,
    @SerializedName("tags") val tags: List<String>,
    @SerializedName("brand") val brand: String,
    @SerializedName("sku") val sku: String,
    @SerializedName("weight") val weight: Int,
    @SerializedName("dimensions") val dimensions: Dimensions,
    @SerializedName("warrantyInformation") val warrantyInformation: String,
    @SerializedName("shippingInformation") val shippingInformation: String,
    @SerializedName("availabilityStatus") val availabilityStatus: String,
    @SerializedName("reviews") val reviews: List<Review>,
    @SerializedName("returnPolicy") val returnPolicy: String,
    @SerializedName("minimumOrderQuantity") val minimumOrderQuantity: Int,
    @SerializedName("meta") val meta: Meta,
    @SerializedName("images") val images: List<String>,
    @SerializedName("thumbnail") val thumbnail: String
)
