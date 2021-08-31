package com.veryable.android.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veryable.android.model.Account
import com.veryable.android.rest.Repository
import kotlinx.coroutines.launch
import retrofit2.Response


class CustomViewModel(private val repository: Repository) : ViewModel() {

    // The list of accounts retrieved from the network, initially empty.
    val accountsLiveData = MutableLiveData<List<Account>>()

    // A message regarding the result of the network request.
    val networkRequestMessage = MutableLiveData<String>()


    /**
     * Gets the accounts from AWS.
     */

    fun getAccounts() {

        // Start a new coroutine for the network request.
        viewModelScope.launch {

            // Attempt to get a response from the network.
            val response: Response<List<Account>>? = repository.getAccounts()

            // If the response is not null.
            if (response != null) {

                // If the response is successful.
                if (response.isSuccessful) {
                    Log.i("Network request", "Successful response")

                    // Update the status of the network request.
                    networkRequestMessage.value = SUCCESSFUL_RESPONSE

                    // Get the list of accounts from the response body.
                    val accounts: List<Account>? = response.body()

                    // Update the LiveData.
                    accountsLiveData.value = accounts

                } else { // If the response is not successful.
                    Log.i("Network request", "Unsuccessful response")
                }
            } else { // If the response is null (network request failed).

                // Update the status of the network request.
                networkRequestMessage.value = NETWORK_REQUEST_FAILED
            }
        }
    }

    companion object {
        const val SUCCESSFUL_RESPONSE = "Successful response"
        const val NETWORK_REQUEST_FAILED = "Network request failed"
    }


}