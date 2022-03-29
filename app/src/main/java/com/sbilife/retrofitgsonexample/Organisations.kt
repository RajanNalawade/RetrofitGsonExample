package com.sbilife.retrofitgsonexample

import com.google.gson.annotations.SerializedName

data class Organisations(

    @field:SerializedName("Organisations")
    val organisations: List<OrganisationsItem?>? = null
)

data class OrganisationsItem(

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("github")
    val github: Any? = null,

    @field:SerializedName("website")
    val website: String? = null,

    @field:SerializedName("role")
    val role: String? = null,

    @field:SerializedName("lastTransactionAmount")
    val lastTransactionAmount: Double? = null,

    @field:SerializedName("profile")
    val profile: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("isActive")
    val isActive: Boolean? = null,

    @field:SerializedName("lastTransactionAt")
    val lastTransactionAt: String? = null,

    @field:SerializedName("MemberId")
    val memberId: Int? = null,

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("twitter")
    val twitter: String? = null,

    @field:SerializedName("tier")
    val tier: String? = null,

    @field:SerializedName("totalAmountDonated")
    val totalAmountDonated: Double? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("currency")
    val currency: String? = null,

    @field:SerializedName("company")
    val company: Any? = null,

    @field:SerializedName("email")
    val email: Any? = null
)
