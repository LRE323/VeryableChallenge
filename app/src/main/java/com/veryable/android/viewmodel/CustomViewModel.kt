package com.veryable.android.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veryable.android.model.Account
import com.veryable.android.rest.Repository
import kotlinx.coroutines.launch
import retrofit2.awaitResponse


class CustomViewModel(private val repository: Repository) : ViewModel() {

    // The list of accounts retrieved from the network, initially empty.
    val accountsLiveData = MutableLiveData<List<Account>>()

    // A message regarding the result of the network request.
    val networkRequestMessage = MutableLiveData<String>()


    /**
     * Gets the accounts from AWS.
     */
    fun getAccounts() {

        // Launch a new coroutine for the network request.
        viewModelScope.launch {

            try {

                // Get the response from the network.
                val response = repository.getAccounts().awaitResponse()

                // If the response is successful.
                if (response.isSuccessful) {

                    Log.i("Request", "Successful response")

                    // Get the list of accounts from the response body.
                    val accounts: List<Account>? = response.body()

                    // Update the LiveData.
                    accountsLiveData.value = accounts

                } else {
                    Log.i("Request", "Unsuccessful response")
                    networkRequestMessage.value = "Unsuccessful response"
                }

            } catch (e: Exception) {
                Log.i("Request", e.toString())
                networkRequestMessage.value = NETWORK_REQUEST_FAILED
            }
        }
    }

    companion object {
        const val NETWORK_REQUEST_FAILED = "Network request failed"
    }


}