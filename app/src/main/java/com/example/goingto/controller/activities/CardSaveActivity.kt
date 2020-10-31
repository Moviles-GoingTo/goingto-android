package com.example.goingto.controller.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.goingto.R
import kotlinx.android.synthetic.main.activity_card_save.*
import kotlinx.android.synthetic.main.activity_card_save.btSave

class CardSaveActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_save)

        val name: String? = intent.getSerializableExtra("Name") as String?
        val surname: String? = intent.getSerializableExtra("Surname") as String?
        val birthdate: String? = intent.getSerializableExtra("Birthdate") as String?
        val email: String? = intent.getSerializableExtra("Email") as String?
        val password: String? = intent.getSerializableExtra("Password") as String?
        val cardNumber: String? = intent.getSerializableExtra("CardNumber") as String?
        val cardEndDate: String?  = intent.getSerializableExtra("CardEndDate") as String?
        val cardCvv: String?  = intent.getSerializableExtra("CardCvv") as String?
        val cardHolder: String?  = intent.getSerializableExtra("CardHolder") as String?

        etCardNumberDetails.setText(cardNumber)
        etCardEndDate.setText(cardEndDate)
        etCardCvv.setText(cardCvv)
        etCardHolder.setText(cardHolder)

        btSave.setOnClickListener {

            val cardNumberToSend = etCardNumberDetails.text.toString()
            val cardEndDateToSend = etCardEndDate.text.toString()
            val cardCvvToSend = etCardCvv.text.toString()
            val cardHolderToSend = etCardHolder.text.toString()

            val registerActivity = Intent(applicationContext, RegisterActivity::class.java)
            registerActivity.putExtra("Name", name)
            registerActivity.putExtra("Surname", surname)
            registerActivity.putExtra("Birthdate", birthdate)
            registerActivity.putExtra("Email", email)
            registerActivity.putExtra("Password", password)
            registerActivity.putExtra("CardNumber", cardNumberToSend)
            registerActivity.putExtra("CardEndDate", cardEndDateToSend)
            registerActivity.putExtra("CardCvv", cardCvvToSend)
            registerActivity.putExtra("CardHolder", cardHolderToSend)
            startActivity(registerActivity)
        }
    }
}