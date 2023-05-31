package com.example.savingsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.savingsapp.data.UserViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Login : AppCompatActivity() {
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        val signupInstead = findViewById<TextView>(R.id.tv_signupinstead)
        val btn_login = findViewById<Button>(R.id.btn_login)

        btn_login.setOnClickListener{
            val email: String = et_login_email.text.toString()
            val password: String = et_login_pass.text.toString()

            if (inputCheck(email, password)) {
                loginUser(email, password)
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        signupInstead.setOnClickListener {
            startActivity(Intent(this@Login, signup::class.java))
            finish()
        }
    }

    private fun inputCheck(email: String, password: String): Boolean {
        return !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)
    }

    private fun loginUser(email: String, password: String) {

        CoroutineScope(Dispatchers.Main).launch {
            val isSuccess = mUserViewModel.loginUser(email, password)
            if (isSuccess) {
                // Login successful
                Toast.makeText(this@Login, "Login successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@Login, Home::class.java))
                finish()
                // Proceed to the next screen or perform desired actions
            } else {
                // Login failed
                Toast.makeText(this@Login, "Incorrect email or password", Toast.LENGTH_SHORT).show()
            }
        }

    }
}