package com.veryable.android.model

import com.google.gson.annotations.SerializedName

data class Account(

    @SerializedName("id")
    val id: String,

    @SerializedName("account_type")
    val type: String,

    @SerializedName("account_name")
    val name: String,

    @SerializedName("desc")
    val description: String,

    ) {

    fun getTransferType(): String {
        var transferType = ""

        when (type) {
            "bank" -> transferType = "Bank Account: ACH - Same Day"
            "card" -> transferType = "Card: Instant"
        }
        return transferType
    }

}