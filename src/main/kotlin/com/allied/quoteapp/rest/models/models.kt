package com.allied.quoteapp.rest.models

import com.allied.quoteapp.entities.*
import java.time.LocalDateTime

data class QuoteFormModel(
        var products: List<Long>,
        var dimension: Long,
        var finishes: List<Long>,
        var woodType: Long,
        var tier: Long,
        var packaging: Long,
        var customerName: String,
        var address: String,
        var contactEmail: String,
        var phone: String,
        var upadatedBy: String = ""

)

data class PagedQuoteViewModel(
        val quoteViewModel: List<QuoteViewModel>,
        val totalPages: Int,
        val currentPage: Int
)

data class QuoteViewModel(
        var id: Long?,
        var products: List<Product>,
        var dimension: Dimension,
        var finishes: List<Finish>,
        var woodType: WoodType,
        var tier: Tier,
        var packaging: Packaging,
        var customer: Customer,
        var updatedAt: LocalDateTime
)

data class CustomerFormModel(
        var name: String,
        var address: String,
        var contactEmail: String,
        var phone: String,
        var updatedBy: String = ""
)