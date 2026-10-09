package com.example.jcbmi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jcbmi.ui.theme.JcbmiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JcbmiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BMIscreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun BMIscreen(){
    var name: String
    var weight: Int = 80
    var height: Int = 180
    var bmi: Float = 0f
    Column(){
        Text(text = "BMI Calculator")
        TextField(
            value = "",
            onValueChange = {name = it},
            label = {Text(text = "Name")}
        )

        Row() {
            Text(text = "Pes: $weight Kg")

            Button(onClick = {weight++}) {
                Text(text = "+")
            }
            Button(onClick = {weight--}) {
                Text(text = "-")
            }
        }
        Text(text = "Height: $height cm")
        Slider(
            value = height.toFloat(),
            onValueChange = {height = it.toInt()},
            valueRange = 100f..250f
        )
        Button(onClick = {
        }) {
            Text(text = "Calculate BMI")
        }
        if (bmi != 0f){
            Text(text = "Your bmi is: $bmi")
        }




    }



}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JcbmiTheme {
        BMIscreen()
    }
}