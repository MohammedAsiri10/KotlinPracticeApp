package com.example.springtrainingproject2

import com.google.gson.annotations.SerializedName

// DomainResponse.kt
data class DomainResponse(
    val domains: List<Domain>,
    val total: Int,
    val time: String?,
    val next_page: String?
)

// Domain.kt
data class Domain(
    val domain: String,
    @SerializedName("create_date") val createDate: String,
    @SerializedName("update_date") val updateDate: String,
    val country: String?,
    @SerializedName("isDead") val isDead: String,  // API returns "False" as String
    val A: List<String>?,
    val NS: List<String>?,
    val CNAME: List<String>?,
    val MX: List<MXRecord>?,
    val TXT: List<String>?
)


data class MXRecord(
    val exchange: String,
    val priority: Int
)

