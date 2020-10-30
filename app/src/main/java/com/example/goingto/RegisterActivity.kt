package com.example.goingto

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.goingto.ui.dialog.DatePickerFragment
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    val genders : ArrayList<String> = arrayListOf("Masculino", "Femenino");
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val name: String? = intent.getSerializableExtra("Name") as String?
        val surname: String? = intent.getSerializableExtra("Surname") as String?
        val birthdate: String? = intent.getSerializableExtra("Birthdate") as String?
        val email: String? = intent.getSerializableExtra("Email") as String?
        val password: String? = intent.getSerializableExtra("Password") as String?
        val cardNumber: String? = intent.getSerializableExtra("CardNumber") as String?
        val cardEndDate: String?  = intent.getSerializableExtra("CardEndDate") as String?
        val cardCvv: String?  = intent.getSerializableExtra("CardCvv") as String?
        val cardHolder: String?  = intent.getSerializableExtra("CardHolder") as String?

        etName.setText(name)
        etSurname.setText(surname)
        etBirthdate.setText(birthdate)
        etEmail.setText(email)
        etPassword.setText(password)
        tvCardNumber.text = cardNumber

        etBirthdate.setOnClickListener {
            showDatePickerDialog()
        }

        tvAdd.setOnClickListener {
            val nameToSend = etName.text.toString()
            val surnameToSend = etSurname.text.toString()
            val birthdateToSend = etBirthdate.text.toString()
            val emailToSend = etEmail.text.toString()
            val passwordToSend = etPassword.text.toString()

            val cardSaveActivity = Intent(applicationContext, CardSaveActivity::class.java)
            cardSaveActivity.putExtra("Name", nameToSend)
            cardSaveActivity.putExtra("Surname", surnameToSend)
            cardSaveActivity.putExtra("Birthdate", birthdateToSend)
            cardSaveActivity.putExtra("Email", emailToSend)
            cardSaveActivity.putExtra("Password", passwordToSend)
            cardSaveActivity.putExtra("CardNumber", cardNumber)
            cardSaveActivity.putExtra("CardEndDate", cardEndDate)
            cardSaveActivity.putExtra("CardCvv", cardCvv)
            cardSaveActivity.putExtra("CardHolder", cardHolder)
            startActivity(cardSaveActivity)
        }

        btSave.setOnClickListener {
            val name = etName.text
            val surname = etSurname.text
            val gender = spGender.selectedItem.toString()
            val birthdate = etBirthdate.text
            val email = etEmail.text
            val password = etPassword.text
            println(name)
            println(surname)
            println(gender)
            println(birthdate)
            println(email)
            println(password)
        }

        // access the spinner
        if (spGender != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item, genders
            )
            spGender.adapter = adapter
        }
    }

    private fun showDatePickerDialog() {
        val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
            // +1 because January is zero
            val selectedDate = day.toString() + " / " + (month + 1) + " / " + year
            etBirthdate.setText(selectedDate)
        })

        newFragment.show(supportFragmentManager, "datePicker")
    }
}