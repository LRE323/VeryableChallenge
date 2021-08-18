package com.veryable.android.rest

import com.veryable.android.model.Account
import retrofit2.Call

class Repository(private val retrofitService: RetrofitService) {

    /**
     * Gets the accounts from AWS.
     */
    fun getAccounts(): Call<List<Account>> {
        return retrofitService.getAccounts()
    }
}