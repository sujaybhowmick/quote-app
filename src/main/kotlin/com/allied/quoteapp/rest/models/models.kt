package com.allied.quoteapp.rest.models

data class QuoteFormModel(
        val name: String?,
        val products: List<String>?,
        val dimension: String?,
        val finish: List<String>?,
        val woodType: String?,
        val tier: String?,
        val packaging: String?,
        val customer: String?
)

data class CustomerFormModel(
    var name: String,
    var address: String,
    var contactEmail: String,
    var phone: String,
    var updatedBy: String = ""
)