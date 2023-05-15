/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.example.hypertensionchecker.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.*
import androidx.compose.ui.Alignment
import androidx.wear.compose.material.Text
import androidx.compose.foundation.rememberScrollState
import com.example.hypertensionchecker.presentation.theme.KNN
import com.example.hypertensionchecker.presentation.database
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.*
import androidx.room.Entity
import androidx.room.PrimaryKey
//import com.example.hypertensionchecker.presentation.MainActivity.ResultScreen()
//import com.example.myapp.R

import java.util.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            InputForm(onSubmit = {})
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "inputScreen") {
                composable("inputScreen") { InputForm(navController) }
                composable("resultScreen") { ResultScreen(navController) }
                composable("loadingScreen") { LoadingScreen(navController) }
                composable("hypertensionScreen") { Hypertension(navController) }
            }
        }
    }


    var  predict2 = 0

    fun cal(age: Int, gender: Int, blood: Int, cholesterol: Int) {
        // Create a KNN model with k=3
        val model = KNN(3)

        // Add some data points to the model
        model.addDataPoint(intArrayOf(50, 1, 130, 200), 1)
        model.addDataPoint(intArrayOf(35, 0, 120, 180), 0)
        model.addDataPoint(intArrayOf(65, 1, 140, 220), 1)
        model.addDataPoint(intArrayOf(40, 0, 125, 190), 0)
        model.addDataPoint(intArrayOf(55, 0, 140, 200), 1)
        model.addDataPoint(intArrayOf(35, 1, 120, 190), 0)
        model.addDataPoint(intArrayOf(65, 0, 150, 230), 1)
        model.addDataPoint(intArrayOf(40, 1, 125, 180), 0)
        model.addDataPoint(intArrayOf(50, 0, 135, 195), 1)
        model.addDataPoint(intArrayOf(60, 1, 145, 225), 1)
        model.addDataPoint(intArrayOf(55, 1, 130, 200), 1)
        model.addDataPoint(intArrayOf(65, 0, 150, 230), 1)
        model.addDataPoint(intArrayOf(45, 1, 130, 190), 0)
        model.addDataPoint(intArrayOf(50, 0, 130, 190), 1)
        model.addDataPoint(intArrayOf(55, 0, 140, 200), 1)
        model.addDataPoint(intArrayOf(35, 1, 120, 190), 0)
        model.addDataPoint(intArrayOf(65, 0, 150, 230), 1)
        model.addDataPoint(intArrayOf(40, 1, 125, 180), 0)
        model.addDataPoint(intArrayOf(50, 0, 135, 195), 1)
        model.addDataPoint(intArrayOf(60, 1, 145, 225), 1)
        model.addDataPoint(intArrayOf(55, 1, 130, 200), 1)
        model.addDataPoint(intArrayOf(65, 0, 150, 230), 1)
        model.addDataPoint(intArrayOf(45, 1, 130, 190), 0)
        model.addDataPoint(intArrayOf(50, 0, 130, 190), 1)
        model.addDataPoint(intArrayOf(55, 0, 140, 200), 1)
        model.addDataPoint(intArrayOf(35, 1, 120, 190), 0)
        model.addDataPoint(intArrayOf(65, 0, 150, 230), 1)
        model.addDataPoint(intArrayOf(40, 1, 125, 180), 0)
        model.addDataPoint(intArrayOf(50, 0, 135, 195), 1)
        model.addDataPoint(intArrayOf(60, 1, 145, 225), 1)
        model.addDataPoint(intArrayOf(55, 1, 130, 200), 1)
        model.addDataPoint(intArrayOf(65, 0, 150, 230), 1)
        model.addDataPoint(intArrayOf(45, 1, 130, 190), 0)
        model.addDataPoint(intArrayOf(50, 0, 130, 190), 1)
        model.addDataPoint(intArrayOf(55, 0, 140, 200), 1)
        model.addDataPoint(intArrayOf(35, 1, 120, 190), 0)
        model.addDataPoint(intArrayOf(65, 0, 150, 230), 1)
        model.addDataPoint(intArrayOf(40, 1, 125, 180), 0)
        model.addDataPoint(intArrayOf(50, 0, 135, 195), 1)
        model.addDataPoint(intArrayOf(60, 1, 145, 225), 1)
        model.addDataPoint(intArrayOf(55, 1, 130, 200), 1)

        // Test the model on a new data point
        val testFeature = intArrayOf(age, gender, blood, cholesterol)
        val predictedLabel = model.classify(testFeature)
        predict2 = predictedLabel
        println("Predicted label: $predictedLabel")
    }


    @Composable
    fun LoadingScreen(navController: NavController) {
        var animationState by remember { mutableStateOf(0f) }
        val infiniteTransition = rememberInfiniteTransition()

        val animation by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(5000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )

        LaunchedEffect(key1 = true) {
            delay(5000)
            animationState = 1f
            navController.navigate("hypertensionScreen")
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Loading...",
                color = Color.White // Set the color to white
            )
        }
    }


    var  name2 = ""
    var  age2 = 0
    var  Gender2 = 0
    var  Cholesterol2 = 0
    var  blood2 = 0

    @Composable
    fun InputForm(navController: NavController) {
        val random = Random()
        var name by remember { mutableStateOf("") }
        var age by remember { mutableStateOf("") }
        var Gender by remember { mutableStateOf("") }
        val BloodPressure = random.nextInt(900)
        var Cholesterol by remember { mutableStateOf("") }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                content = {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Name") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.White // Set the text color to white
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = age,
                        onValueChange = { age = it },
                        label = { Text("Age") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.White // Set the text color to white
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = Gender,
                        onValueChange = { Gender = it },
                        label = { Text("Gender(1 for male, 0 for female)") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.White // Set the text color to white
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = Cholesterol,
                        onValueChange = { Cholesterol = it },
                        label = { Text("Cholesterol") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.White // Set the text color to white
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            // Save the input to a variable
                            val input = "Name: $name\nAge: $age"
                            // Add your code here to handle the input
                            // For example, you can print the input to the console
                                              var age = age.toInt()
                                              var gender = Gender.toInt()
                                              var cholesterol = Cholesterol.toInt()
                            cal(age, gender, BloodPressure, cholesterol)
                            name(name, age, gender, BloodPressure, cholesterol)
                            println(input)
                            navController.navigate("resultScreen")

                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Save Input")

                    }


                }


            )

        }


    }

     fun name (name: String, age: Int, Gender: Int, BloodPressure: Int, Cholesterol: Int){
        name2 = name
        age2 = age
        Gender2 = Gender
        blood2 = BloodPressure
        Cholesterol2 = Cholesterol
    }

    @Composable
    fun ResultScreen(navController: NavController) {
//    LoadingScreen(onLoadingComplete = ::InputForm())
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Button(onClick = {navController.navigate("loadingScreen")  }) {
                Text("Check Blood Pressure")
            }
        }
    }

    @Composable
    fun Hypertension(navController: NavController){
        val datar = database().databases(4,"life", 3, 5, 6, 4, 7)
//        println(datar)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                content = {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Name: $name2",
                        modifier = Modifier.padding(start = 16.dp),
                        color = Color.White // Set the color to white
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Age: $age2",
                        color = Color.White // Set the color to white
                    )

                    Spacer(modifier = Modifier.height(16.dp))
//
                    Text(
                        text = "Gender: $Gender2",
                        color = Color.White // Set the color to white
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Cholesterol: $Cholesterol2",
                        color = Color.White // Set the color to white
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Blood pressure: $blood2",
                        color = Color.White // Set the color to white
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    if (predict2 == 1){
                        Text(

                            text = "Machine Model Verdict: \n You Likely have Hypertension",
                            fontSize = 8.sp,
                            color = Color.White // Set the color to white
                        )
                    }else{
                        Text(

                            text = "Machine Model Verdict: \n You don't have Hypertension",
                            fontSize = 8.sp,
                            color = Color.White // Set the color to white
                        )
                    }


                    Spacer(modifier = Modifier.height(16.dp))
                }


            )
//
        }

    }





    @Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
    @Composable
    fun DefaultPreview() {
//    navController.navigate("secondScreen")
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "inputScreen") {
            composable("inputScreen") { InputForm(navController) }
            composable("resultScreen") { ResultScreen(navController) }
            composable("loadingScreen") { LoadingScreen(navController) }
            composable("hypertensionScreen") { Hypertension(navController) }
        }
    }
}