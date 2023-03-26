package com.example.postappcompose.ui.launch

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postappcompose.data.models.User
import com.example.postappcompose.data.models.UserWithFavorite
import com.example.postappcompose.data.repository.UserRepository
import com.example.postappcompose.extension.EMAIL_ADDRESS
import com.example.postappcompose.extension.PASSWORD
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
@Singleton
class LaunchViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    internal val email = mutableStateOf("")
    internal val password = mutableStateOf("")
    internal val errorMail = mutableStateOf("")
    internal val errorPass = mutableStateOf("")

    fun loginUser(
        email: String,
        pass: String,
        onStart: () -> Unit,
        onResult: (result: UserWithFavorite?) -> Unit,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                onStart()
            }
            val resultUser: UserWithFavorite =
                userRepository.loginUser(email, pass)
            withContext(Dispatchers.Main) {
                onResult(resultUser)
            }
        }
    }

    fun insertUser(vararg users: User, onStart: () -> Unit, onResult: (done: Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                onStart()
            }
            val resultUser: Int =
                userRepository.getCountUserMail(users[0].email)
            var done = false
            if (resultUser == 0) {
                userRepository.insertUser(*users)
                done = true
            }
            withContext(Dispatchers.Main) {
                onResult(done)
            }
        }
    }

    fun validEmail(mail: String): String = if (EMAIL_ADDRESS.matches(mail)) "" else "Email is Valid"

    fun validPassWord(pass: String): String =
        if (PASSWORD.matches(pass)) "" else "Wrong password format"
}