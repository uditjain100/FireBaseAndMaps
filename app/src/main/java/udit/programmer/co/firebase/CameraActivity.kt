package udit.programmer.co.firebase

import android.content.pm.PackageManager
import android.opengl.Matrix
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Rational
import android.view.Surface
import android.view.ViewGroup
import androidx.camera.core.CameraX
import androidx.camera.core.Preview
import androidx.camera.core.PreviewConfig
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_camera.*
import java.util.jar.Manifest

class CameraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            texture_layout.post {
                setCamera()
            }
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CAMERA),
                1234
            )
        }

    }

    private fun setCamera() {
        val previewConfig = PreviewConfig.Builder().apply {
            setTargetAspectRatio(Rational.POSITIVE_INFINITY)
            setLensFacing(CameraX.LensFacing.BACK)
        }.build()
        val preview = Preview(previewConfig)
        preview.setOnPreviewOutputUpdateListener {
            val parent = texture_layout.parent as ViewGroup
            parent.removeView(texture_layout)
            parent.addView(texture_layout, 0)
            updateCamera()
            texture_layout.surfaceTexture = it.surfaceTexture
        }
        CameraX.bindToLifecycle(this, preview)
    }

    private fun updateCamera() {
        val matrix = android.graphics.Matrix()
        val centerX = texture_layout.width / 2f
        val centerY = texture_layout.height / 2f
        val rotationDegree = when (texture_layout.display.rotation) {
            Surface.ROTATION_0 -> 0
            Surface.ROTATION_90 -> 90
            Surface.ROTATION_180 -> 180
            Surface.ROTATION_270 -> 270
            else -> return
        }
        matrix.postRotate(rotationDegree.toFloat(), centerX, centerY)
        texture_layout.setTransform(matrix)
    }
}
