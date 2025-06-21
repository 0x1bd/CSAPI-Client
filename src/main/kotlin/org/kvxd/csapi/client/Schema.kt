package org.kvxd.csapi.client

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val name: String,
    val skins: Set<Skin>,
)

@Serializable
data class Skin(
    val name: String,
    val minFloat: Float,
    val maxFloat: Float,
    val collection: String,
    val rarity: Rarity,
    val wears: List<Wear>,
    val stattrakWears: List<Wear> = emptyList(),
    val souvenirWears: List<Wear> = emptyList(),
    val image: String
)

@Serializable
data class Rarity(
    val name: String,
    val index: Int
)

@Serializable
data class Wear(
    val name: String,
    val marketHashName: String,
    val skinportNormal: SkinportNormal?,
    val skinportMinimal: SkinportMinimal?
)

@Serializable
data class SkinportNormal(
    val last24Hours: HistoricPriceData,
    val last7Days: HistoricPriceData,
    val last30Days: HistoricPriceData,
    val last90Days: HistoricPriceData,
)

@Serializable
data class HistoricPriceData(
    val min: Double? = null,
    val max: Double? = null,
    @SerialName("avg")
    val average: Double? = null,
    val median: Double? = null,
    val volume: Int
)

@Serializable
data class SkinportMinimal(
    val suggestedPrice: Double? = null,
    val averageSalePrice: Double? = null,
    val volumeLast90Days: Int
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

@Serializable
data class MarketHashResponse(
    val weapon: String,
    val skin: Skin,
    val wear: Wear
)