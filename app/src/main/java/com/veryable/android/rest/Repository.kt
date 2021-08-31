package com.veryable.android.rest

import android.util.Log
import com.veryable.android.model.Account
import retrofit2.Response
import retrofit2.awaitResponse

class Repository(private val retrofitService: RetrofitService) {

    /**
     * Gets the accounts from AWS.
     */
    suspend fun getAccounts(): Response<List<Account>>? {

        // The variable for the response, initially null.
        var response: Response<List<Account>>? = null

        // Attempt the network request.
        try {

            // Set the value for the response.
            response = retrofitService.getAccounts().awaitResponse()

        } catch (e: Exception) {
            Log.i("Network request", e.message.toString())

        }
        return response

    }
}