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
    val image: String,
    val listings: List<Listing>,
    val stattrakListings: List<Listing>,
    val souvenirListings: List<Listing>,
)

@Serializable
data class Listing(
    val float: Float,
    val price: Double,
    val url: String,
    val link: String,
    val stickers: List<Sticker> = emptyList(),
    val charms: List<Charm> = emptyList(),
)

@Serializable
data class Sticker(
    val img: String,
    val name: String,
    val slot: Int
)

@Serializable
data class Charm(
    val name: String,
    val img: String,
    val pattern: Int
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