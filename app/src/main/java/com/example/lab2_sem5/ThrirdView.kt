package com.example.lab2_sem5


import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Temperature(){
    var Click by remember { mutableStateOf(false) }
    val context = LocalContext.current //dane z lokalnego telefonu
    var temperature by remember { mutableStateOf(0f) } //zmienna trzymająca intensywność światłą
    var sensorManager: SensorManager? = null
    var temperatureSensor: Sensor? = null

    DisposableEffect(context) {
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        temperatureSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)

        val sensorEventListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                event?.let {
                    temperature = it.values[0]

                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            }
        }

        sensorManager?.registerListener(
            sensorEventListener,
            temperatureSensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )

        onDispose {
            sensorManager?.unregisterListener(sensorEventListener)
        }
    }
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

                Button(
                    onClick = { }
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
                    text = "Temperatura: $temperature °C",
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                )
            }

        }



    }



}
