package com.veryable.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.veryable.android.model.Account

class CustomAdapter :
    RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

    // The list of accounts retrieved from the network, initially empty.
    private val dataSet: MutableList<Account> = mutableListOf()

    // The onClickListener for each account item.
    private val listener: View.OnClickListener = View.OnClickListener {  }

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

        // Get element from your dataset at this position and replace the contents of the view with
        // that element.
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

        // Add all the values from the list to the dataset
        dataSet.addAll(accounts)

        // Notify the observers.
        notifyDataSetChanged()
    }

    inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        // The UI objects that will be updated with account details.
        private val accountName: TextView = view.findViewById(R.id.accountName)
        private val accountDescription: TextView = view.findViewById(R.id.accountDescription)
        private val accountTransferType: TextView = view.findViewById(R.id.accountTransferType)
        private val accountImage: ImageView = view.findViewById(R.id.accountImage)

        // The OnClickListener for each account item.
        private val onClickListener: View.OnClickListener = View.OnClickListener {

            // Get the position of the item.
            val position = adapterPosition
            Toast.makeText(itemView.context, "You clicked on item ${position + 1}", Toast.LENGTH_SHORT).show()
        }

        fun bind(account: Account) {

            // Set the text for the account details.
            accountName.text = account.name
            accountDescription.text = account.description
            accountTransferType.text = account.getTransferType()

            // Choose the account image that will be displayed.
            if (account.type == "bank") {
                accountImage.setImageResource(R.drawable.baseline_account_balance_black_48pt_1x)
            } else {
                accountImage.setImageResource(R.drawable.baseline_credit_card_black_48pt_1x)
            }

            // Set the OnClickListener.
            itemView.setOnClickListener(onClickListener)
        }

    }

}