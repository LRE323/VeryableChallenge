package com.veryable.android.activity

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.veryable.android.R
import com.veryable.android.adapter.CustomAdapter
import com.veryable.android.model.Account
import com.veryable.android.rest.APIClient
import com.veryable.android.rest.Repository
import com.veryable.android.rest.RetrofitService
import com.veryable.android.viewmodel.CustomViewModel

class AccountsActivity : AppCompatActivity() {

    /**
     * The ViewModel for this activity.
     */
    private val viewModel: CustomViewModel =
        CustomViewModel(Repository(APIClient.retrofit.create(RetrofitService::class.java)))

    /**
     * The RecyclerView for the list of accounts.
     */
    private lateinit var rvAccounts: RecyclerView

    /**
     * The adapter for the RecyclerView.
     */
    private val adapter = CustomAdapter()

    /**
     * The TextView for the status of the network request.
     */
    private lateinit var tvNetworkStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accounts)

        // Initialize the network status TextView.
        tvNetworkStatus = findViewById(R.id.tvNetworkStatus)

        // Initialize the RecyclerView.
        initRecyclerView()

        // Initialize observations.
        initObservation()

        // Attempt to retrieve the list of accounts from the network.
        viewModel.getAccounts()
    }

    /**
     * Initializes the RecyclerView.
     */
    private fun initRecyclerView() {

        // Create the RecyclerView for the accounts.
        rvAccounts = findViewById(R.id.accountsRecyclerView)

        // Add the adapter to the RecyclerView.
        rvAccounts.adapter = adapter

        // Set the layout manager for the RecyclerView.
        rvAccounts.layoutManager = LinearLayoutManager(this)
    }

    /**
     * Initializes observations.
     */
    private fun initObservation() {

        // Create the observer for the list of accounts.
        val observerAccounts = Observer<List<Account>> { accounts ->

            // Submit the retrieved list of accounts to the adapter.
            adapter.submitList(accounts)
        }

        // Observe the list of accounts.
        viewModel.accountsLiveData.observe(this, observerAccounts)

        // Create the observer for the network request result.
        val observerNetworkRequestMessage = Observer<String> { message ->

            // If the response is successful.
            if (message == CustomViewModel.SUCCESSFUL_RESPONSE) {

                // Display the RecyclerView.
                rvAccounts.visibility = View.VISIBLE

                // Hide the network status text.
                tvNetworkStatus.visibility = View.GONE
            }

            // If the network request fails.
            if (message == CustomViewModel.NETWORK_REQUEST_FAILED) {

                // Display text saying the network request failed.
                tvNetworkStatus.text = getString(R.string.network_request_failed)

            }

        }

        // Observe the network request message.
        viewModel.networkRequestMessage.observe(this, observerNetworkRequestMessage)

    }
}