package udit.programmer.co.firebase.Sensors

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_other_sensors.*
import udit.programmer.co.firebase.R

class OtherSensorsActivity : AppCompatActivity(), SensorEventListener {

    lateinit var sensorManager: SensorManager
    lateinit var lightSensor: Sensor
    lateinit var pressureSensor: Sensor
    lateinit var temperatureSensor: Sensor
    lateinit var humiditySensor: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_sensors)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        if (sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE) == null) {
            pressure_value_tv.text = "Pressure Sensor Not Supported"
        } else {
            pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)
            sensorManager.registerListener(this, pressureSensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
        if (sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) == null) {
            temp_value_tv.text = "Temperature Sensor Not Supported"
        } else {
            temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
            sensorManager.registerListener(
                this,
                temperatureSensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
        if (sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY) == null) {
            humidity_value_tv.text = "Humidity Sensor Not Supported"
        } else {
            humiditySensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY)
            sensorManager.registerListener(this, humiditySensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }


    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    @SuppressLint("SetTextI18n")
    override fun onSensorChanged(event: SensorEvent?) {

        if (event!!.sensor.type == Sensor.TYPE_LIGHT)
            light_value_tv.text = event.values[0].toString()
        else if (event.sensor.type == Sensor.TYPE_PRESSURE)
            pressure_value_tv.text = event.values[0].toString()
        else if (event.sensor.type == Sensor.TYPE_AMBIENT_TEMPERATURE)
            temp_value_tv.text = event.values[0].toString()
        else if (event.sensor.type == Sensor.TYPE_RELATIVE_HUMIDITY)
            humidity_value_tv.text = event.values[0].toString()

    }

}