package udit.programmer.co.firebase.Sensors

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_accelerometer.*
import udit.programmer.co.firebase.R

class AccelerometerActivity : AppCompatActivity(), SensorEventListener {

    lateinit var sensorManager: SensorManager
    lateinit var accelerometor: Sensor
    var temp = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accelerometer)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, accelerometor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    @SuppressLint("SetTextI18n")
    override fun onSensorChanged(event: SensorEvent?) {
        val x = event!!.values[0]
        val y = event.values[1]
        val z = event.values[2]

        x_tv.text = "X : $x"
        y_tv.text = "Y : $y"
        z_tv.text = "Z : $z"

        sufflingWork(x, y, z)
    }

    private fun sufflingWork(x: Float, y: Float, z: Float) {
        val sqrt =
            (x * x + y * y + z * z) / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH)
        if (sqrt >= 3) {
            Toast.makeText(this, "Device Shuffled", Toast.LENGTH_LONG).show()
            if (temp) accelerometer_layout.setBackgroundColor(Color.RED)
            else accelerometer_layout.setBackgroundColor(Color.CYAN)
        }
        temp = !temp
    }
}