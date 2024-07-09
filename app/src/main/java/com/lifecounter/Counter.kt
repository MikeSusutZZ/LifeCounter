import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lifecounter.R
import kotlin.math.max
import kotlin.math.min

@Composable
fun CounterScreen() {
    var facingPlayer by remember { mutableStateOf(100) }
    var awayPlayer by remember { mutableStateOf(100) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.spaceshipbackground),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // Adjust the scaling to cover the full background
        )

        // Darken layer
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f)) // Adjust alpha to make it darker or lighter
        )

        // Your existing CounterScreen content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CounterColumn(180F, awayPlayer,
                dealDamage = { damage -> facingPlayer -= damage },
                recoverHealth = { heal -> awayPlayer += heal })

            CounterColumn(0F, facingPlayer,
                dealDamage = { damage -> awayPlayer -= damage },
                recoverHealth = { heal -> facingPlayer += heal })
        }
    }
}

@Composable
fun CounterColumn(
    rotationDegrees: Float,
    thisPlayerHealth: Int,
    dealDamage: (Int) -> Unit,
    recoverHealth: (Int) -> Unit
) {
    var damage by remember { mutableStateOf(0) }
    var heal by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .graphicsLayer {
                rotationZ = rotationDegrees  // Apply rotation
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("${thisPlayerHealth}", fontSize = 60.sp, color = getColorBasedOnValue(thisPlayerHealth))
        Spacer(Modifier.height(20.dp))
        // Damage counter
        Text("Damage", fontSize = 20.sp)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = { damage -= 10 }) { Text("-10") }
            Spacer(Modifier.width(4.dp))
            Button(onClick = { damage -= 1 }) { Text("-1") }
            Spacer(Modifier.width(12.dp))
            Text("${damage}", fontSize = 20.sp)
            Spacer(Modifier.width(12.dp))
            Button(onClick = { damage += 1 }) { Text("+1") }
            Spacer(Modifier.width(4.dp))
            Button(onClick = { damage += 10 }) { Text("+10") }
        }

        // Healing counter
        Spacer(Modifier.height(6.dp))
        Text("Heal", fontSize = 20.sp)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = { heal -= 10 }) { Text("-10") }
            Spacer(Modifier.width(4.dp))
            Button(onClick = { heal -= 1 }) { Text("-1") }
            Spacer(Modifier.width(12.dp))
            Text("${heal}", fontSize = 20.sp)
            Spacer(Modifier.width(12.dp))
            Button(onClick = { heal += 1 }) { Text("+1") }
            Spacer(Modifier.width(4.dp))
            Button(onClick = { heal += 10 }) { Text("+10") }
        }
        Spacer(Modifier.height(10.dp))

        // Enter button to apply changes
        Button(onClick = {
            dealDamage(damage)
            recoverHealth(heal)
            damage = 0 // Reset damage
            heal = 0   // Reset heal
        }) {
            Text("Enter", fontSize = 20.sp)
        }
    }
}
fun getColorBasedOnValue(value: Int): Color {
    val adjustedValue = min(100, max(0, value))
    val greenAmount: Float
    val redAmount: Float

    if (adjustedValue >= 50) {
        // From 50 to 100, green is 1, red decreases from 1 to 0
        greenAmount = 1f
        redAmount = 1 - ((adjustedValue - 50) / 50f)
    } else {
        // From 0 to 50, green decreases from 1 to 0, red is 1
        greenAmount = adjustedValue / 50f
        redAmount = 1f
    }

    return Color(red = redAmount, green = greenAmount, blue = 0f)
}
