package com.veryable.android.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.veryable.android.R
import com.veryable.android.model.Account

class DetailsActivity : AppCompatActivity() {

    // The UI objects for account information.
    private lateinit var ivAccount: ImageView
    private lateinit var tvAccountName: TextView
    private lateinit var tvAccountDescription: TextView

    // The buttons that will finish the activity.
    private lateinit var btnDone: Button
    private lateinit var btnBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        // Initialize the UI objects.
        initUserInterfaceObjects()

        // Update the UI with account information.
        updateUserInterface()
    }

    /**
     * Initializes the UI objects that will be worked with.
     */
    private fun initUserInterfaceObjects() {

        // The UI objects that hold account information.
        ivAccount = findViewById(R.id.accountImageInDetailsActivity)
        tvAccountName = findViewById(R.id.accountNameInDetailsActivity)
        tvAccountDescription = findViewById(R.id.accountDescriptionInDetailsActivity)

        // The buttons that will finish the activity.
        btnDone = findViewById(R.id.buttonDone)
        btnBack = findViewById(R.id.buttonBack)

        // Set the OnClickListeners.
        setOnClickListeners()

    }

    /**
     * Updates the UI with information from the account extra in the intent.
     */
    private fun updateUserInterface() {

        // Get the account extra from the intent.
        val account: Account? = intent.getParcelableExtra("account")

        // Null check for the extra.
        if (account != null) {

            // Update the ImageView with the appropriate image.
            if (account.accountType == Account.BANK) {
                ivAccount.setImageResource(R.drawable.baseline_account_balance_black_48pt_2x)
            } else {

                // Else, the account type is card.
                ivAccount.setImageResource(R.drawable.baseline_credit_card_black_48pt_2x)
            }

            // Update the TextViews with account information.
            tvAccountName.text = account.accountName
            tvAccountDescription.text = account.description
        }
    }

    /**
     * Sets all the OnClickListeners for this activity.
     */
    private fun setOnClickListeners() {

        // The listener for the done and back buttons.
        val onClickListener: View.OnClickListener = View.OnClickListener {

            // Finish the activity and return to the activity that called this one.
            finish()
        }

        // Set the OnClickListeners.
        btnDone.setOnClickListener(onClickListener)
        btnBack.setOnClickListener(onClickListener)
    }
}