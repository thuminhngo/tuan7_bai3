package com.example.formsubmit

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var nameInput: EditText
    private lateinit var idInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var phoneInput: EditText
    private lateinit var dateOfBirthInput: EditText
    private lateinit var addressInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameInput = findViewById(R.id.isName)
        idInput = findViewById(R.id.isID)
        emailInput = findViewById(R.id.isEmail)
        phoneInput = findViewById(R.id.phoneNumber)
        dateOfBirthInput = findViewById(R.id.dateOfBirth)

        setupDatePicker()
    }

    private fun setupDatePicker() {
        dateOfBirthInput.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    val selectedDate = String.format("%02d/%02d/%d", selectedDay, selectedMonth + 1, selectedYear)
                    dateOfBirthInput.setText(selectedDate)
                },
                year,
                month,
                day
            ).show()
        }
    }

    private fun validateForm(): Boolean {
        var isValid = true;

        if (nameInput.text.toString().trim().isEmpty()) {
            nameInput.error = "Không được để trống tên"
            isValid = false
        }

        if (idInput.text.toString().trim().isEmpty()) {
            idInput.error = "Không được để trống mã số sinh viên"
            isValid = false
        }

        if (emailInput.text.toString().trim().isEmpty()) {
            emailInput.error = "Không được để trống email"
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailInput.text.toString()).matches()) {
            emailInput.error = "Hãy điền một email hợp lệ"
            isValid = false
        }

        if (phoneInput.text.toString().trim().isEmpty()) {
            phoneInput.error = "Không được để trống số điện thoại"
            isValid = false
        }

        if (dateOfBirthInput.text.toString().trim().isEmpty()) {
            dateOfBirthInput.error = "Không được để trống ngày sinh"
            isValid = false
        }

        if (!isValid) {
            Toast.makeText(this, "Hãy điền đầy đủ các thông tin", Toast.LENGTH_SHORT).show()
        }

        return isValid
    }

    private fun submitForm() {
        if (validateForm()) {
            Toast.makeText(this, "Gửi thông tin thành công", Toast.LENGTH_SHORT).show()
        }
    }
}