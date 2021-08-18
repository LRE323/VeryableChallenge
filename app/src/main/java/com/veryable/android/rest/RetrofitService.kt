package com.veryable.android.rest

import com.veryable.android.model.Account
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    /**
     * Gets the accounts from AWS.
     */
    @GET("/veryable.json")
    fun getAccounts(): Call<List<Account>>

}