package com.veryable.android.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.veryable.android.CustomAdapter
import com.veryable.android.R
import com.veryable.android.model.Account
import com.veryable.android.rest.APIClient
import com.veryable.android.rest.Repository
import com.veryable.android.rest.RetrofitService
import com.veryable.android.viewmodel.CustomViewModel

class AccountsActivity : AppCompatActivity() {

    /**
     * The ViewModel for this activity.
     */
    private lateinit var viewModel: CustomViewModel

    /**
     * The RecyclerView for the list of accounts.
     */
    private lateinit var recyclerView: RecyclerView

    /**
     * The adapter for the RecyclerView.
     */
    private val adapter = CustomAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Initialize the ViewModel
        initViewModel()

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
        recyclerView = findViewById(R.id.accountsRecyclerView)

        // Add the adapter to the RecyclerView.
        recyclerView.adapter = adapter

        // Set the layout manager for the RecyclerView.
        recyclerView.layoutManager = LinearLayoutManager(this)
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

        // Create the observer for the network request message.
        val observerNetworkRequestMessage = Observer<String> {

            // Show a toast with the network request message.
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        // Observe the network request message.
        viewModel.networkRequestMessage.observe(this, observerNetworkRequestMessage)

    }

    /**
     * Initializes the ViewModel.
     */
    private fun initViewModel() {
        viewModel =
            CustomViewModel(Repository(APIClient.retrofit.create(RetrofitService::class.java)))
    }

}