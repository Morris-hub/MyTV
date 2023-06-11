import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlin.math.sqrt

class ShakeDetectorView(
    private val context: Context,
    private val onShake: () -> Unit
) : SensorEventListener {
    private var sensorManager: SensorManager? = null
    private var accelerometer: Sensor? = null
    private var lastX: Float = 0f
    private var lastY: Float = 0f
    private var lastZ: Float = 0f
    private var lastTimestamp: Long = 0

    private val shakeThreshold = 800
    private val minTimeBetweenShakes = 500

    init {
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    fun startShakeDetection() {
        sensorManager?.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    fun stopShakeDetection() {
        sensorManager?.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Not needed
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val currentTime = System.currentTimeMillis()
        val timeDifference = currentTime - lastTimestamp

        if (timeDifference >= minTimeBetweenShakes) {
            val x = event?.values?.get(0) ?: 0f
            val y = event?.values?.get(1) ?: 0f
            val z = event?.values?.get(2) ?: 0f

            val accelerationDifference = calculateAccelerationDifference(x, y, z)

            if (accelerationDifference >= shakeThreshold) {
                onShake.invoke()
                lastTimestamp = currentTime
            }

            lastX = x
            lastY = y
            lastZ = z
        }
    }

    private fun calculateAccelerationDifference(x: Float, y: Float, z: Float): Float {
        val accelerationX = x - lastX
        val accelerationY = y - lastY
        val accelerationZ = z - lastZ

        return sqrt(accelerationX * accelerationX + accelerationY * accelerationY + accelerationZ * accelerationZ)
    }
}
