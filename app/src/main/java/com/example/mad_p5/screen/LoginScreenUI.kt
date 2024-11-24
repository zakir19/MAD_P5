package com.example.mad_p5.screen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.mad_p5.R
import com.example.mad_p5.navigateToRegistrationScreen

@Composable
fun LoginForm(context: Context, navController: NavController) {
    var  email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
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
            shape = MaterialTheme.shapes.medium, modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)

            ) {
                FormField(label = "Email", textState = email, onTextChange ={email=it} )
                FormField(label = "Password", isPasswordField = true, textState = password, onTextChange = {password=it})
                Text(text = "Forgot Password?",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(bottom = 35.dp, top = 10.dp)
                        .align(Alignment.End)
                )
                Button(onClick = { /*TODO*/ }, modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)) {
                    Text(text = "login", fontSize = 18.sp)

                }
//                Row {
//                    Text(text = "Email", modifier = Modifier
//                        .align(Alignment.CenterVertically))
//                    TextField(
//                        value = "email", onValueChange = {}, modifier = Modifier
//                            .padding(top = 0.dp)
//                            .wrapContentSize()
//                    )
//                }
//                Row {
//                    Column {
//                        Text(text = "Password")
//                        TextField(value = "password", onValueChange = {}, modifier = Modifier)
//                    }
//                }

            }
        }
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Column {
                Text(text = "Don't have an account? ",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(top = 26.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
            Column {
//                Button(onClick = { /*TODO*/ }) {
//
//                }
                Text(text = "SIGN UP",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(top = 26.dp)
                        .align(Alignment.CenterHorizontally)
                        .clickable {
                            navigateToRegistrationScreen(navController)
                        }
                )
            }
        }

    }
}
@Composable
fun FormField(
    label:String,
    textState: String,
    onTextChange: (String) -> Unit,
    isNumber:Boolean=false,
    isPasswordField:Boolean=false){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(text = label, fontSize = 18.sp, modifier = Modifier
            .weight(1f)
            .align(Alignment.CenterVertically))
        if (isNumber){
            OutlinedTextField(
                label={ Text("enter $label") },
                placeholder = { Text("enter $label") },
                value=textState,
                onValueChange = onTextChange,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(2f),
                textStyle = LocalTextStyle.current.copy(fontSize = 18.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = VisualTransformation.None
            )
        }
        else{
            OutlinedTextField(
                label={ Text("enter $label") },
                placeholder = { Text("enter $label") },
                value=textState,
                onValueChange = {newValue ->
                    if (newValue.all { it.isDigit() }){
                        onTextChange(newValue)
                    }
                },
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(2f),
                textStyle = LocalTextStyle.current.copy(fontSize = 18.sp),
                visualTransformation =
                if(isPasswordField)
                    PasswordVisualTransformation()
                else
                    VisualTransformation.None
            )

        }
    }
}