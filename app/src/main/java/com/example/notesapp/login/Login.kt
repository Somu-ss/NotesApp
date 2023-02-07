package com.example.notesapp.login

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notesapp.ui.theme.NotesAppTheme


@SuppressLint("SuspiciousIndentation")
@Composable
fun LoginScreen(
        loginViewModel: LoginViewModel? = null,
        onNavToHomePage: () -> Unit,
        onNavToSignupPage: () -> Unit

) {
    val loginUiState = loginViewModel?.loginUiState
    val isError = loginUiState?.loginError != null
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 200.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Login",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.primary
            )

            if (isError) {
                Text(text = loginUiState?.loginError ?: "Error Unknown", color = Color.Red)
            }

            OutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
                value = loginUiState?.userName?:"",
                onValueChange ={loginViewModel?.onUserNameChange(it)},
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Person,
                        contentDescription ="UserName" )
                },
                label = {
                    Text(text = "Email")
                },
                isError = isError)

            OutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 18.dp, end = 18.dp, bottom = 10.dp),
                value = loginUiState?.password?:"",
                onValueChange ={loginViewModel?.onPasswordChange(it)},
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock,
                        contentDescription ="Password" )
                },
                label = {
                    Text(text = "Password")
                },
                visualTransformation = PasswordVisualTransformation(),
                isError = isError)
            
            Button(onClick = { loginViewModel?.loginUser(context) }) {
                Text(text = "Log In",
                        fontSize = 17.sp)
            }
            Spacer(modifier = Modifier.size(18.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                Text(text = "Don't Have An Account?")
                Spacer(modifier = Modifier.size(8.dp))
                TextButton(onClick = { onNavToSignupPage.invoke() }) {
                    Text(text = "SignUp")
                }
                
            }
            if (loginUiState?.isLoading == true){
                CircularProgressIndicator()
            }
            LaunchedEffect(key1 = loginViewModel?.containUser) {
                if (loginViewModel?.containUser==true) {
                    onNavToHomePage.invoke()
                }
            }

        }
    }
}

@Composable
fun SignUpScreen(
    loginViewModel: LoginViewModel? = null,
    onNavToHomePage: () -> Unit,
    onNavToLoginPage: () -> Unit

) {
    val loginUiState = loginViewModel?.loginUiState
    val isError = loginUiState?.signUpError != null
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 200.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "SignUp",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.primary
            )

            if (isError) {
                Text(text = loginUiState?.signUpError ?: "Error Unknown", color = Color.Red)
            }

            OutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
                value = loginUiState?.signUpuserName?:"",
                onValueChange ={loginViewModel?.signUpOnUserNameChange(it)},
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Person,
                        contentDescription ="UserName" )
                },
                label = {
                    Text(text = "Email")
                },
                isError = isError)

            OutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 18.dp, end = 18.dp, bottom = 10.dp),
                value = loginUiState?.signUppassword?:"",
                onValueChange ={loginViewModel?.signUpPasswordChange(it)},
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock,
                        contentDescription ="Password" )
                },
                label = {
                    Text(text = "Password")
                },
                visualTransformation = PasswordVisualTransformation(),
                isError = isError)

            OutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 18.dp, end = 18.dp, bottom = 10.dp),
                value = loginUiState?.signUpconfrmpassword?:"",
                onValueChange ={loginViewModel?.onConformPasswordChange(it)},
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock,
                        contentDescription ="Password" )
                },
                label = {
                    Text(text = "Conform Password")
                },
                visualTransformation = PasswordVisualTransformation(),
                isError = isError)

            Button(onClick = { loginViewModel?.createUser(context) }) {
                Text(text = "Create Account",
                    fontSize = 17.sp)
            }
            Spacer(modifier = Modifier.size(18.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                Text(text = "Already Have An Account?")
                Spacer(modifier = Modifier.size(8.dp))
                TextButton(onClick = { onNavToLoginPage.invoke() }) {
                    Text(text = "Log In", fontSize = 16.sp)
                }

            }
            if (loginUiState?.isLoading == true){
                CircularProgressIndicator()
            }
            LaunchedEffect(key1 = loginViewModel?.containUser) {
                if (loginViewModel?.containUser == true) {
                    onNavToHomePage.invoke()
                }
            }

        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PrevLoginScreen() {
    NotesAppTheme {
         LoginScreen(onNavToHomePage = { /*TODO*/ }) {
             
         }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PrevSignUpScreen() {
    NotesAppTheme {
        SignUpScreen(onNavToHomePage = { /*TODO*/ }) {
            
        }
    }
}
    
