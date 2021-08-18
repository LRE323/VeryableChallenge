package com.veryable.android.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Account(

    @SerializedName("id")
    val id: String,

    @SerializedName("account_type")
    val accountType: String,

    @SerializedName("account_name")
    val accountName: String,

    @SerializedName("desc")
    val description: String,

    ) : Parcelable {

    /**
     * Returns the transfer type base on the account type.
     */
    fun getTransferType(): String {

        // By default, no transfer type.
        var transferType = ""

        when (accountType) {
            "bank" -> transferType = "Bank Account: ACH - Same Day"
            "card" -> transferType = "Card: Instant"
        }
        return transferType
    }

    companion object {
        const val BANK = "bank"
    }

}