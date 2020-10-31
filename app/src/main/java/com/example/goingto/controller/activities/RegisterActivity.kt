package com.example.goingto.controller.activities

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.goingto.R
import com.example.goingto.model.User
import com.example.goingto.network.UserService
import com.example.goingto.ui.dialog.DatePickerFragment
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime

class RegisterActivity : AppCompatActivity() {
    val genders : ArrayList<String> = arrayListOf("Masculino", "Femenino");

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        ui(applicationContext)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun ui(applicationContext: Context) {
        // get extra values
        val name: String? = intent.getSerializableExtra("Name") as String?
        val surname: String? = intent.getSerializableExtra("Surname") as String?
        val birthdate: String? = intent.getSerializableExtra("Birthdate") as String?
        val email: String? = intent.getSerializableExtra("Email") as String?
        val password: String? = intent.getSerializableExtra("Password") as String?
        val cardNumber: String? = intent.getSerializableExtra("CardNumber") as String?
        val cardEndDate: String?  = intent.getSerializableExtra("CardEndDate") as String?
        val cardCvv: String?  = intent.getSerializableExtra("CardCvv") as String?
        val cardHolder: String?  = intent.getSerializableExtra("CardHolder") as String?

        // set extra values to input texts
        etName.setText(name)
        etSurname.setText(surname)
        etBirthdate.setText(birthdate)
        etEmail.setText(email)
        etPassword.setText(password)
        tvCardNumber.text = cardNumber

        // set listeners
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
            saveUser(applicationContext)
            val userId = getSavedUserId()
            saveUserProfile(userId)

            val mainActivity = Intent(applicationContext, MainActivity::class.java)
            startActivity(mainActivity)
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveUserProfile(userId: Int) {
        val name = etName.text.toString()
        val surname = etSurname.text.toString()
        val gender = spGender.selectedItem.toString()
        val birthdate = etBirthdate.text.toString()
        val countryId = 0
        val createdAt = LocalDateTime.now().toString()

        println("saveUserProfile")
        val sharedPreferences: SharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.apply{
            putString("name", name)
            putString("surname", surname)
            putString("birthdate", birthdate)
            putString("gender", gender)
            putInt("countryId", countryId)
            putString("createdAt", createdAt)
        }.apply()
    }

    private fun saveUser(context: Context) {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api-goingto.azurewebsites.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val userService: UserService
        userService = retrofit.create(UserService::class.java)

        val user = User(null, email, password, null, null)
        val response = userService.saveUser(user).enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("Activity Fail", "Error: " + t.toString())
            }

            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ) {
                if (response.isSuccessful) {
                    println("user saved")
                }
            }
        })
    }

    private fun getSavedUserId(): Int {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()
        var userId = -1;

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api-goingto.azurewebsites.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val userService: UserService
        userService = retrofit.create(UserService::class.java)

        val user = User(null, email, password, null, null)
        val response = userService.authenticate(user).enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("Activity Fail", "Error: " + t.toString())
            }

            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ) {
                if (response.isSuccessful) {
                    println("authenticateUser")
                    userId = response.body()!!.id!!
                }
            }
        })
        return userId
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