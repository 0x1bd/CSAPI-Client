package org.kvxd.csapi.client

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val name: String,
    val skins: Set<Skin>,
    val lastUpdated: Long
)

@Serializable
data class Skin(
    val name: String,
    val minFloat: Float,
    val maxFloat: Float,
    val collection: String,
    val rarity: Rarity,
    val listings: List<Listing>,
    val stattrakListings: List<Listing>,
    val souvenirListings: List<Listing>,
    val image: String
)

@Serializable
data class Listing(
    val float: Float,
    val price: Double,
    val url: String,
    val link: String
)

@Serializable
data class Rarity(
    val name: String,
    val index: Int
)

@Serializable
data class SkinSuggestion(
    val weapon: String,
    val name: String,
    val collection: String,
    val rarity: Rarity,
    val image: String
)

@Serializable
data class ApiResponse<T>(
    val success: Boolean,
    val data: T
)

@Serializable
data class ErrorResponse(
    val code: Int,
    val message: String,
    val timestamp: Long
)