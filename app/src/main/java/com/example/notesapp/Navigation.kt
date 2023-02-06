package com.example.notesapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.home.Home
import com.example.notesapp.login.LoginScreen
import com.example.notesapp.login.LoginViewModel
import com.example.notesapp.login.SignUpScreen

enum class LoginRoutes{
    SignUp,
    LogIn
}

enum class HomeRoutes{
    Home,
    Details
}

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
    loginViewModel: LoginViewModel
) {
    NavHost(navController = navController, startDestination = LoginRoutes.LogIn.name)
    {
        composable(route = LoginRoutes.LogIn.name){
            LoginScreen(onNavToHomePage = {
                navController.navigate(HomeRoutes.Home.name){
                    launchSingleTop = true
                    popUpTo(route = LoginRoutes.LogIn.name){
                        inclusive = true
                    }
                }
            },
                loginViewModel = loginViewModel) {
                navController.navigate(LoginRoutes.SignUp.name){
                    launchSingleTop = true
                    popUpTo(route = LoginRoutes.SignUp.name){
                        inclusive = true
                    }
                }


            }
        }

        composable(route = LoginRoutes.SignUp.name){
            SignUpScreen(onNavToHomePage = {
                navController.navigate(HomeRoutes.Home.name){
                    popUpTo(LoginRoutes.SignUp.name) {
                        inclusive = true

                    }
                }
            },
             loginViewModel = loginViewModel) {
                navController.navigate(LoginRoutes.LogIn.name)
            }
        }

        composable(route = HomeRoutes.Home.name){
            Home()
        }
    }


}