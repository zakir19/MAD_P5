package com.example.mad_p5.screen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mad_p5.R
import com.example.mad_p5.navigateToLoginScreen

@Composable
fun RegistrationForm(context: Context, navController: NavController) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.guni_pink_logo),
            contentDescription = "Guni Logo",
            modifier = Modifier
                .height(130.dp)
                .wrapContentSize()
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Fit
        )

        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                FormFieldRegister(label = "Name", textState = name, onTextChange = { name = it })
                FormFieldRegister(label = "Phone Number", textState = phone, onTextChange = { phone = it }, isNumber = true)
                FormFieldRegister(label = "City", textState = city, onTextChange = { city = it })
                FormFieldRegister(label = "Email", textState = email, onTextChange = { email = it })
                FormFieldRegister(label = "Password", isPasswordField = true, textState = password, onTextChange = { password = it })
                FormFieldRegister(label = "Confirm Password", isPasswordField = true, textState = confirmPassword, onTextChange = { confirmPassword = it })

                Button(
                    onClick = { /* Handle registration */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 16.dp)
                ) {
                    Text(text = "Register", fontSize = 18.sp)
                }
            }
        }

        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Column {
                Text(
                    text = "Already have an account? ",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(top = 26.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
            Column {
                Text(
                    text = "LOGIN",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(top = 26.dp)
                        .align(Alignment.CenterHorizontally)
                        .clickable{
                            (navigateToLoginScreen(navController))
                        }
                )
            }
        }
    }
}

@Composable
fun FormFieldRegister(
    label: String,
    textState: String,
    onTextChange: (String) -> Unit,
    isNumber: Boolean = false,
    isPasswordField: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 18.sp,
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )

        if (isNumber) {
            OutlinedTextField(
                label = { Text("Enter $label") },
                placeholder = { Text("Enter $label") },
                value = textState,
                onValueChange = onTextChange,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(2f),
                textStyle = LocalTextStyle.current.copy(fontSize = 18.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                visualTransformation = VisualTransformation.None
            )
        } else {
            OutlinedTextField(
                label = { Text("Enter $label") },
                placeholder = { Text("Enter $label") },
                value = textState,
                onValueChange = { newValue ->
                    if (newValue.all { it.isDigit() }) {
                        onTextChange(newValue)
                    }
                },
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(2f),
                textStyle = LocalTextStyle.current.copy(fontSize = 18.sp),
                visualTransformation = if (isPasswordField) PasswordVisualTransformation() else VisualTransformation.None
            )
        }
    }
}