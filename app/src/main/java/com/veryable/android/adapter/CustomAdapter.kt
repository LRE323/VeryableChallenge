package com.veryable.android.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.veryable.android.R
import com.veryable.android.activity.DetailsActivity
import com.veryable.android.model.Account

class CustomAdapter :
    RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

    // The list of accounts retrieved from the network, initially empty.
    private val dataSet: MutableList<Account> = mutableListOf()

    /**
     * Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent
     * an item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        // Create a new view, which defines the UI of the list item.
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_account, parent, false)

        return CustomViewHolder(view)
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the RecyclerView.ViewHolder.itemView to reflect the item at the given
     * position.
     */
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        // Update the contents to reflect the item at the give position.
        holder.bind(dataSet[position])
    }


    /**
     * Returns the total number of items in the data set held by the adapter.
     */
    override fun getItemCount(): Int {
        return dataSet.size
    }

    /**
     * Updates the list of accounts once it has been retrieved from the network.
     */
    fun submitList(accounts: List<Account>) {

        // Remove all values in the list, so there are no duplicates.
        dataSet.clear()

        // Add all the values from the list to the dataset.
        dataSet.addAll(accounts)

        // Notify the observers.
        notifyDataSetChanged()
    }

    inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        // The UI objects that will display account details.
        private val tvAccountName: TextView = view.findViewById(R.id.accountName)
        private val tvAccountDescription: TextView = view.findViewById(R.id.accountDescription)
        private val tvAccountTransferType: TextView = view.findViewById(R.id.accountTransferType)
        private val ivAccount: ImageView = view.findViewById(R.id.accountImageInRecyclerView)

        // The OnClickListener for each account item.
        private val onClickListener: View.OnClickListener = View.OnClickListener {

            // Get the account at the position clicked.
            val account: Account = dataSet[adapterPosition]

            // Create a new intent.
            val intent = Intent(view.context, DetailsActivity::class.java)

            // Add the extra to the intent.
            intent.putExtra("account", account)

            // Launch the Activity.
            view.context.startActivity(intent)
        }

        /**
         * Binds the account information to the UI.
         */
        fun bind(account: Account) {

            // Set the text for the account details.
            tvAccountName.text = account.accountName
            tvAccountDescription.text = account.description
            tvAccountTransferType.text = account.getTransferType()

            // Choose the account image that will be displayed.
            if (account.accountType == Account.BANK) {
                ivAccount.setImageResource(R.drawable.baseline_account_balance_black_48pt_1x)
            } else {

                // Else, the account is a card.
                ivAccount.setImageResource(R.drawable.baseline_credit_card_black_48pt_1x)
            }

            // Set the OnClickListener.
            itemView.setOnClickListener(onClickListener)
        }

    }

}