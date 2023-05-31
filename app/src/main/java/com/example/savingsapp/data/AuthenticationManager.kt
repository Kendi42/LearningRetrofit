package com.example.savingsapp.data

import org.mindrot.jbcrypt.BCrypt

class AuthenticationManager(private val userDao: UserDao) {
    suspend fun registerUser(id: Int, email: String, name: String, password: String) {
        val user = User(id, email, name, password)
        userDao.addUser(user)
        // Handle successful registration
    }

    suspend fun loginUser(email: String, password: String): Boolean {
        val user = userDao.getUserByEmail(email)

        return if (user != null){
            val passwordHash= user.passwordHash
            BCrypt.checkpw(password, passwordHash)
        }
        else{
            false
        }
    }
}
