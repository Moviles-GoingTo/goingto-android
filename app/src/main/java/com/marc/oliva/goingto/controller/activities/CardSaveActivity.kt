package com.marc.oliva.goingto.controller.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.marc.oliva.goingto.R
import kotlinx.android.synthetic.main.activity_card_save.*
import kotlinx.android.synthetic.main.activity_card_save.btSave

class CardSaveActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_save)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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

            val sharedPreferences: SharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.apply{
                putString("cardNumber", cardNumberToSend)
                putString("cardEndDate", cardEndDateToSend)
                putString("cardCvv", cardCvvToSend)
                putString("cardHolder", cardHolderToSend)
            }.apply()

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
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}