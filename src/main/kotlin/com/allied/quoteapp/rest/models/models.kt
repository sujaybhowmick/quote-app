package com.allied.quoteapp.rest.models

import com.allied.quoteapp.entities.*

data class CustomerQuoteModel(
        val id: Long,
        val name: String,
        val products: List<Product>,
        val dimension: Dimension,
        val finish: Finish,
        val woodType: WoodType,
        val tier: Tier,
        val packaging: Packaging,
        val customer: Customer
)