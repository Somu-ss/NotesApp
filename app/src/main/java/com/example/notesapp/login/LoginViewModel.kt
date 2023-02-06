package com.example.notesapp.login

import android.content.Context
import android.text.BoringLayout
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: AuthRepository = AuthRepository()
): ViewModel(){

    val currentUser = repository.currentUser

    val containUser:Boolean
    get() = repository.containUser()

    var loginUiState by mutableStateOf(LoginUiState())
        private set


    fun onUserNameChange(userName: String){
        loginUiState = loginUiState.copy(userName = userName)
    }

    fun onPasswordChange(password: String){
        loginUiState = loginUiState.copy(password = password)
    }

    fun signUpOnUserNameChange(signUpUserName: String){
        loginUiState = loginUiState.copy(signUpuserName = signUpUserName)
    }

    fun signUpPasswordChange(signUppassword: String){
        loginUiState = loginUiState.copy(signUppassword = signUppassword)
    }

    fun onConformPasswordChange(signUpConformPassword: String){
        loginUiState = loginUiState.copy(signUpconfrmpassword = signUpConformPassword)
    }


    private fun validateLoginForm() = loginUiState.userName.isNotBlank() &&
            loginUiState.password.isNotBlank()

    private fun validateSignupForm() = loginUiState.signUpuserName.isNotBlank() &&
            loginUiState.signUppassword.isNotBlank() &&
            loginUiState.signUpconfrmpassword.isNotBlank()

    fun createUser(context: Context) = viewModelScope.launch {
        try {
            if(!validateSignupForm()){
                throw java.lang.IllegalArgumentException("Email and password can not be empty")
            }
            loginUiState = loginUiState.copy(isLoading = true)

            if (
                loginUiState.signUppassword !=
                loginUiState.signUpconfrmpassword) {
                throw IllegalArgumentException("Password Does Not Match")
            }
            loginUiState = loginUiState.copy(signUpError = null)

            repository.createUser(
                loginUiState.signUpuserName,
                loginUiState.signUppassword){isSuceesful ->
                if(isSuceesful){
                    Toast.makeText(context,
                        "Successfully Logged In",
                        Toast.LENGTH_SHORT).show()
                    loginUiState = loginUiState.copy(isSuccesslogin = true)
                }else{
                    Toast.makeText(context,
                        "Login Failed",
                        Toast.LENGTH_SHORT).show()
                    loginUiState = loginUiState.copy(isSuccesslogin = false)
                }
            }
        }catch (e:java.lang.Exception){
            loginUiState = loginUiState.copy(signUpError = e.localizedMessage)
            e.printStackTrace()

        }finally {
            loginUiState = loginUiState.copy(isLoading = false)
        }

    }

    fun loginUser(context: Context) = viewModelScope.launch {
        try {
            if(!validateLoginForm()){
                throw java.lang.IllegalArgumentException("Email and password can not be empty")
            }
            loginUiState = loginUiState.copy(isLoading = true)

            loginUiState = loginUiState.copy(loginError = null)

            repository.createUser(
                loginUiState.userName,
                loginUiState.password){isSuceesful ->
                if(isSuceesful){
                    Toast.makeText(context,
                        "Successfully Logged In",
                        Toast.LENGTH_SHORT).show()
                    loginUiState = loginUiState.copy(isSuccesslogin = true)
                }else{
                    Toast.makeText(context,
                        "Login Failed",
                        Toast.LENGTH_SHORT).show()
                    loginUiState = loginUiState.copy(isSuccesslogin = false)
                }
            }
        }catch (e:java.lang.Exception){
            loginUiState = loginUiState.copy(loginError = e.localizedMessage)
            e.printStackTrace()

        }finally {
            loginUiState = loginUiState.copy(isLoading = false)
        }

    }

}

data class LoginUiState(
    val userName: String = "",
    val password: String = "",
    val signUpuserName: String = "",
    val signUppassword: String = "",
    val signUpconfrmpassword: String = "",
    val isLoading: Boolean = false,
    val isSuccesslogin: Boolean = false,
    val signUpError: String? = null,
    val loginError:String? = null

)