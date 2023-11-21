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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LightSensor(onNavigateToThirdView: () -> Unit){
    var click by remember { mutableStateOf(false) }
    var showThirdView by remember { mutableStateOf(false) }
    val context = LocalContext.current //dane z lokalnego telefonu
    var lightSensivity by remember { mutableStateOf(0f) } //zmienna trzymająca intensywność światłą
    var sensorManager: SensorManager? = null
    var lightSensor: Sensor? = null

    DisposableEffect(context) {
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        lightSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_LIGHT)

        val sensorEventListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                event?.let {
                    lightSensivity = it.values[0]
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            }
        }

        sensorManager?.registerListener(
            sensorEventListener,
            lightSensor,
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

                Text(
                    text="Przejdź do trzeciego ekranu"
                )
                Spacer(modifier=Modifier.width(10.dp))
                Button(
                    modifier=Modifier.align(Alignment.CenterVertically),
                    onClick = {onNavigateToThirdView() }
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Add")


                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    click = true
                },

                ) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        }

    ){ innerPadding ->
        if (click) {


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    color = Color(lightSensivity/100000,lightSensivity/100000,lightSensivity/100000),
                    modifier = Modifier.padding(8.dp),
                    text = "Intensywność światła: $lightSensivity lux",
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                )
            }

        }
        if (showThirdView) {
            Temperature()
        }

        }


    }



