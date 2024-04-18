package com.example.tapp

import android.credentials.CreateCredentialResponse
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tapp.ui.theme.TappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TappTheme {
                Myapp()
            }
        }
    }
}


@Composable
fun Myapp() {
    // this mutable state form is oprated by using "Variablename.value"
    var MoneyCounter = remember { //var is used to change the value of the variable
        mutableStateOf(0)
    }
    Scaffold(modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.inverseSurface,) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
                verticalArrangement =  Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

            Text(text = "$${MoneyCounter.value}",
                modifier = Modifier.padding(16.dp),
                style = TextStyle(color = Color.White,
                fontSize = 40.sp),
                fontWeight = FontWeight.ExtraBold)

            Spacer(modifier = Modifier.height(50.dp))

            CreateCircle(MoneyCounter.value){ newvalue ->
                MoneyCounter.value = newvalue + 1
                //MoneyCounter.value = it + 1 //by default we can use this "it" to increament the value of the variable
            }

            Spacer(modifier = Modifier.height(50.dp))

            Card(modifier = Modifier
                .padding(16.dp)
                .height(60.dp)
                .width(130.dp)
                .clickable {
                    Log.d("Counter", "CreateCircle: MoneyCounter")
                    MoneyCounter.value = 0
                },
                shape = RectangleShape,
                elevation = CardDefaults.cardElevation(50.dp)) {
                Box(modifier = Modifier.fillMaxSize().background(Color.Unspecified), contentAlignment = Alignment.Center){
                    Text(text = "Reset", modifier = Modifier.padding(16.dp), fontSize = 20.sp)
                }
            }
        }
    }
}


@Composable
fun CreateCircle(MoneyCounter: Int = 0, updateMoneyCounter: (Int) -> Unit = {}){ //Int is given as to satisfy the initial value
    //unit is same as void in java

    //This method can be accessed directly from the variable name
   // var MoneyCounter by remember {
    //    mutableStateOf(0)
   // }
    Card(modifier = Modifier
        .padding(16.dp)
        .size(200.dp)
        .clickable {
            Log.d("Counter", "CreateCircle: MoneyCounter")
            //MoneyCounter.value += 1
            updateMoneyCounter(MoneyCounter)
        },
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(100.dp)) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Text(text = "Tap $MoneyCounter", modifier = Modifier.padding(16.dp), fontSize = 45.sp)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TappTheme {
        Myapp()
    }
}