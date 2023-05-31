package com.example.savingsapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.mindrot.jbcrypt.BCrypt

class UserViewModel (application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<User>>
    private val repository: UserRepository
    private val userDao: UserDao = UserDatabase.getDatabase(application).userDao()
    private val authenticationManager: AuthenticationManager= AuthenticationManager(userDao)

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    private fun hashPassword(password: String): String {
        val saltRounds = 10 // Number of salt rounds for BCrypt
        return BCrypt.hashpw(password, BCrypt.gensalt(saltRounds))
    }

    fun addUser(user: User) {
        val hashedPassword = hashPassword(user.passwordHash)
        val userWithHashedPassword = user.copy(passwordHash = hashedPassword)
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(userWithHashedPassword)
        }
    }

    suspend fun loginUser(email: String, password: String): Boolean {
        return authenticationManager.loginUser(email, password)
    }
}