package com.example.savingsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.savingsapp.data.User
import com.example.savingsapp.data.UserDao
import com.example.savingsapp.data.UserDatabase
import com.example.savingsapp.data.UserViewModel
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.w3c.dom.Text

class signup : AppCompatActivity() {
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val logininstead= findViewById<TextView>(R.id.tv_logininstead)

        logininstead.setOnClickListener {
            startActivity(Intent(this@signup, Login::class.java))
            finish()
        }

        val signupButton: Button = findViewById(R.id.btn_signup)
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        signupButton.setOnClickListener{
            Toast.makeText(this, "Signup Button Clicked", Toast.LENGTH_SHORT).show()
            insertDataToDatabase()
        }
    }

    private fun insertDataToDatabase() {
        val email:String = et_signup_email.text.toString()
        val name: String = et_name_signup.text.toString()
        val password: String =et_signup_pass.text.toString()


        if(inputCheck(email, name, password)){
            // create a user Object
            val user= User(0, email, name, password)
            mUserViewModel.addUser(user)
        }
        else{
            Toast.makeText(this, "Empty field or incorrect format", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(email: String, name: String, password: String): Boolean {

        val emailPattern = Patterns.EMAIL_ADDRESS
        val isEmailValid = emailPattern.matcher(email).matches()
        val isPasswordValid = password.length >= 6
        return !TextUtils.isEmpty(email) && !TextUtils.isEmpty(name) && isEmailValid && isPasswordValid
    }
}