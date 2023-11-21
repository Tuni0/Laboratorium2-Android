package com.example.lab2_sem5

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab2_sem5.ui.theme.Lab2_Sem5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab2_Sem5Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting() {
    var Click by remember { mutableStateOf(false) }
    var showSecondView by remember { mutableStateOf(false) }
    var showThirdView by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = Color.Black,

        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = Color(0xFF242424),
                    titleContentColor = Color(0xB3FFFFFF),
                ),
                title = {
                    Text("Top app bar")
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFF242424),
                contentColor = Color(0xB3FFFFFF),
            ) {

                Text(
                    text="Przejdź do drugiego ekranu"
                )
                Spacer(modifier=Modifier.width(10.dp))

                Button(
                    modifier=Modifier.align(Alignment.CenterVertically),
                    onClick = {showSecondView = true }
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Add")


                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    Click = true
                },

                ) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        }

    ){ innerPadding ->
        if (Click) {


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    color = Color(0xFFFFFFFF),
                    modifier = Modifier.padding(8.dp),
                    text = "",
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                )
            }

        }



    }
    if (showSecondView) {
        AnimatedVisibility(visible = true) {
            LightSensor { showThirdView = true }
        }
    }


    if (showThirdView) {
        AnimatedVisibility(visible = true) {
            Temperature()
        }
    }


}





