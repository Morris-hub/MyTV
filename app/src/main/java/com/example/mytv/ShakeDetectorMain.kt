import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import kotlin.random.Random

class ShakeDetectorMain : ComponentActivity() {
    private lateinit var sensorManager: SensorManager
    private lateinit var shakeDetector: ShakeDetectorView

    private val movieList1 = listOf("Film 1", "Film 2", "Film 3") // Replace with your own list
    private val movieList2 = listOf("Film 4", "Film 5", "Film 6") // Replace with your own list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        shakeDetector = ShakeDetectorView(this) { chooseRandomMovie() }

        setContent {
            MovieShakeApp(onShake = {}) // Specify the type as View here
        }
    }

    private fun chooseRandomMovie() {
        val randomListIndex = Random.nextInt(2) // Randomly choose between the two lists
        val randomMovieIndex =
            Random.nextInt(if (randomListIndex == 0) movieList1.size else movieList2.size)
        val randomMovie =
            if (randomListIndex == 0) movieList1[randomMovieIndex] else movieList2[randomMovieIndex]

        // You can use the selected movie here, for example, display it in a TextView
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(
            shakeDetector,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(shakeDetector)
    }
}

@Composable
fun MovieShakeApp(onShake: () -> Unit) {
    val context = LocalContext.current
    val shakeDetectorView = remember { ShakeDetectorView(context, onShake) }

    DisposableEffect(Unit) {
        shakeDetectorView.startShakeDetection()
        onDispose {
            shakeDetectorView.stopShakeDetection()
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Shake your phone to choose a random movie!",
            modifier = Modifier.padding(bottom = 8.dp)
        )
        AndroidView(
            factory = { context ->
                shakeDetectorView as View
            },
            update = {
                // No need to set the onShakeListener here
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieShakeApp(onShake = {})
}
