package com.example.mvvmretrofitkotlin.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("firstName") val firstName: String? = null,
    @SerializedName("lastName") val lastName: String? = null,
    @SerializedName("age") val age: Int? = null,
    @SerializedName("email") val email: String? = null,
    @SerializedName("phone") val phone: String? = null,
    @SerializedName("hair") val hair: Hair? = null,
    @SerializedName("address") val address: Address? = null
) : Parcelable

@Parcelize
data class Hair(
    @SerializedName("color") val color: String? = null,
    @SerializedName("type") val type: String? = null
) : Parcelable

@Parcelize
data class Address(
    @SerializedName("address") val address: String? = null,
    @SerializedName("city") val city: String? = null,
    @SerializedName("state") val state: String? = null,
    @SerializedName("postalCode") val postalCode: String? = null
) : Parcelable