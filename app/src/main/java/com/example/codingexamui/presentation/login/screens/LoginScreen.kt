package com.example.codingexamui.presentation.login.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.codingexamui.R
import com.example.codingexamui.presentation.login.LoginViewModel
import com.example.codingexamui.presentation.login.event.LoginEvent
import com.example.codingexamui.presentation.login.event.LoginUiEvent
import com.example.codingexamui.presentation.login.state.LoginState
import com.example.codingexamui.ui.theme.AzureRadiance
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var userName by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var userNameError by rememberSaveable {  mutableStateOf<String?>(null) }
    var passwordError by rememberSaveable {  mutableStateOf<String?>(null) }

    val onValueChangeUserName = remember {
        { _userName: String ->
            userName = _userName
            userNameError = null
        }
    }
    val onValueChangePassword = remember {
        { _password: String ->
            password = _password
            passwordError = null
        }
    }

    val onClickLogin = remember { {
        viewModel.onEvent(LoginEvent.Login(userName, password))
    } }


    LaunchedEffect(true) {

        viewModel.event.collectLatest {
            when (it) {
                is LoginUiEvent.LoginSuccess -> {
                    navController.navigate("home")
                }
                is LoginUiEvent.UserNameError -> {
                    userNameError = it.message
                }
                is LoginUiEvent.PasswordError -> {
                    passwordError = it.message
                }
            }
        }
    }
    LoginScreenContent(
        state = state,
        onValueChangeUserName = onValueChangeUserName,
        onValueChangePassword = onValueChangePassword,
        onClickLogin = onClickLogin,
        userName = userName,
        password = password,
        userNameError = userNameError,
        passwordError = passwordError
    )
}


@Composable
private fun LoginScreenContent(
    state: LoginState,
    userName: String = "",
    password: String = "",
    userNameError: String? = "",
    passwordError: String? = "",
    onValueChangeUserName: (String) -> Unit,
    onValueChangePassword: (String) -> Unit,
    onClickLogin: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.wave_top),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopStart)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Login",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = AzureRadiance
            )
            Spacer(modifier = Modifier.height(16.dp))

           Column {
               TextField(
                   value = userName,
                   onValueChange = onValueChangeUserName,
                   label = { Text("Username") },
                   shape = RoundedCornerShape(12.dp),
                   modifier = Modifier.fillMaxWidth(),
                   colors = TextFieldDefaults.colors(
                       focusedIndicatorColor = Color.Transparent,
                       unfocusedIndicatorColor = Color.Transparent,
                       disabledIndicatorColor = Color.Transparent,
                       errorIndicatorColor = Color.Red
                   ),
                   isError = userNameError != null
               )

               userNameError?.let {
                   Text(
                       text = it,
                       color = Color.Red,
                       fontSize = 12.sp,
                       modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                   )
               }
           }

            Spacer(modifier = Modifier.height(8.dp))


            var passwordVisible by remember { mutableStateOf(false) }


            Column {
                TextField(
                    value = password,
                    onValueChange = onValueChangePassword,
                    label = { Text("Password") },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent
                    ),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                painterResource(
                                    if (passwordVisible) R.drawable.baseline_visibility_24 else R.drawable.baseline_visibility_off_24
                                ),
                                contentDescription = if (passwordVisible) "Hide password" else "Show password"
                            )
                        }
                    }
                )
                passwordError?.let {
                    Text(
                        text = it,
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                    )
                }


            }
            Spacer(modifier = Modifier.height(16.dp))

                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = AzureRadiance),
                    onClick = onClickLogin,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Login", fontSize = 18.sp)
                }

        }

        Image(
            painter = painterResource(id = R.drawable.wave_bottom),
            contentDescription = "Wave",
            modifier = Modifier
                .align(Alignment.BottomEnd)
        )
        if(state.isLoading){
            CircularProgressIndicator(
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.Center))
        }

    }
}

@Preview(name = "Phone Preview", device = "spec:width=411dp,height=891dp")
@Composable
private fun PreviewLoginScreen_Phone() {
    LoginScreenContent(
        state = LoginState(),
        onValueChangeUserName = {},
        onValueChangePassword = {},
        onClickLogin = {},
    )
}

@Preview(name = "Tablet Preview", device = "spec:width=1280dp,height=800dp,dpi=240")
@Composable
private fun PreviewLoginScreen_Tablet() {
    LoginScreenContent(
        state = LoginState(),
        onValueChangeUserName = {},
        onValueChangePassword = {},
        onClickLogin = {},
    )
}

@Preview(name = "Landscape Preview", widthDp = 720, heightDp = 480)
@Composable
private fun PreviewLoginScreen_Landscape() {
    LoginScreenContent(
        state = LoginState(),
        onValueChangeUserName = {},
        onValueChangePassword = {},
        onClickLogin = {},
    )
}

@Preview(name = "Large Screen Preview", widthDp = 1080, heightDp = 1920)
@Composable
private fun PreviewLoginScreen_LargeScreen() {
    LoginScreenContent(
        state = LoginState(),
        onValueChangeUserName = {},
        onValueChangePassword = {},
        onClickLogin = {},
    )
}
