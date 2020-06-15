package udit.programmer.co.firebase.Sensors

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sensors.*
import udit.programmer.co.firebase.R
import java.lang.Exception

class SensorsActivity : AppCompatActivity(), SensorEventListener {

    var sensor: Sensor? = null
    var sensorManager: SensorManager? = null
    var isRunning = false
    var mp: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensors)

        accelerometer_btn.setOnClickListener {
            startActivity(Intent(this, AccelerometerActivity::class.java))
        }

        gyroscope_btn.setOnClickListener {
            startActivity(Intent(this, GyroScopeActivity::class.java))
        }

        magnetometer_btn.setOnClickListener {
            startActivity(Intent(this, MagnetometerActivity::class.java))
        }

        other_btn.setOnClickListener {
            startActivity(Intent(this, OtherSensorsActivity::class.java))
        }


        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event!!.values[0] > 30 && !isRunning) {
            isRunning = true
            try {
//                mp = MediaPlayer()
//                mp!!.setDataSource("https://s1.vocaroo.com/media/download_temp/Vocaroo_s1IgjP1TWkrt.mp3")
//                mp!!.prepare()
//                mp!!.start()
                tv.setBackgroundColor(Color.RED)
                Toast.makeText(this, "Brightness Increases", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {

            }
        }

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}