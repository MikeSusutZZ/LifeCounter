package com.lifecounter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CounterScreen() {
    Column(modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally) {
        //Top
        CounterColumn(180F)

        //Spacer(modifier = Modifier.width(16.dp))

        // Bot
        CounterColumn(0F)
    }
}

@Composable
fun CounterColumn(rotation: Float) {
    var bigNumber by remember { mutableIntStateOf(100) }
    var damage by remember { mutableIntStateOf(0) }
    var coin by remember {
        mutableIntStateOf(0)
    }

    Column( modifier = Modifier.rotate(rotation), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(text = "$bigNumber", fontSize = 60.sp)
        //Spacer(modifier = Modifier.height(20.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Text("Damage")
                Spacer(modifier = Modifier.height(8.dp))
                Row {

                    Button(onClick = { damage-- }) {
                        Text(text = "-", fontSize = 20.sp)
                    }
                    Spacer(modifier = Modifier.width(40.dp))
                    Text(text = "$damage", fontSize = 30.sp)
                    Spacer(modifier = Modifier.width(40.dp))
                    Button(onClick = { damage++ }) {
                        Text(text = "+", fontSize = 20.sp)
                    }
                }
                //Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    bigNumber -= damage
                    damage = 0
                }) {
                    Text(text = "Enter", fontSize = 15.sp)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Text("Coin")

                Spacer(modifier = Modifier.height(8.dp))
                Row {

                    Button(onClick = { if(coin > 0) coin-- }) {
                        Text(text = "-", fontSize = 20.sp)
                    }
                    Spacer(modifier = Modifier.width(40.dp))
                    Text(text = "$coin", fontSize = 30.sp)
                    Spacer(modifier = Modifier.width(40.dp))
                    Button(onClick = { coin++ }) {
                        Text(text = "+", fontSize = 20.sp)
                    }

                        
                    }
                Button(onClick = {coin = 0}) { Text(text = "Reset", fontSize = 15.sp)
                }
                //Spacer(modifier = Modifier.height(55.dp))
            }

        }
    }
}


