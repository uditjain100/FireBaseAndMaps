package udit.programmer.co.firebase

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.util.Rational
import android.view.Surface
import android.view.ViewGroup
import androidx.camera.core.*
import androidx.core.app.ActivityCompat
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata
import kotlinx.android.synthetic.main.activity_camera.*
import java.io.File
import java.util.concurrent.Executor

class CameraActivity : AppCompatActivity(), Executor {
    override fun execute(command: Runnable) {
        command.run()
    }

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

        val analyzerConfig = ImageAnalysisConfig.Builder().apply {
            val thread = HandlerThread("Label").apply {
                start()
            }
            setCallbackHandler(
                Handler(thread.looper)
            )

            setImageReaderMode(ImageAnalysis.ImageReaderMode.ACQUIRE_LATEST_IMAGE)
        }.build()

//        val analyzerConfig = ImageAnalysisConfig.Builder().apply {
//            setImageReaderMode(ImageAnalysis.ImageReaderMode.ACQUIRE_LATEST_IMAGE)
//        }

        val analyzerCase = ImageAnalysis(analyzerConfig).apply {
            setAnalyzer(LabelAnalyzer())
        }

        val imageCaptureConfig = ImageCaptureConfig.Builder().apply {
            setTargetAspectRatio(Rational.POSITIVE_INFINITY)
            setCaptureMode(ImageCapture.CaptureMode.MAX_QUALITY)
        }.build()

        val imageCapture = ImageCapture(imageCaptureConfig)

        capture_image.setOnClickListener {
            val file = File(externalMediaDirs.first(), "${System.currentTimeMillis()}.jpg")
            imageCapture.takePicture(file, object : ImageCapture.OnImageSavedListener {
                override fun onImageSaved(file: File) {
                    Log.i("IMAGE CAPTURED", "Image is Captured")
                }

                override fun onError(
                    useCaseError: ImageCapture.UseCaseError,
                    message: String,
                    cause: Throwable?
                ) {
                    Log.i("IMAGE CAPTURED", "Error in Capturing")
                }

            })
        }

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
        CameraX.bindToLifecycle(this, preview, imageCapture)

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

    inner class LabelAnalyzer : ImageAnalysis.Analyzer {
        override fun analyze(image: ImageProxy, rotationDegrees: Int) {
            val x = image.planes[0]
            val y = image.planes[1]
            val z = image.planes[2]

            val xb = x.buffer.remaining()
            val yb = y.buffer.remaining()
            val zb = z.buffer.remaining()

            val data = ByteArray(xb + yb + zb)

            x.buffer.get(data, 0, xb)
            y.buffer.get(data, xb, yb)
            z.buffer.get(data, xb + yb, zb)

            val result: Int = when (rotationDegrees) {
                0 -> FirebaseVisionImageMetadata.ROTATION_0
                90 -> FirebaseVisionImageMetadata.ROTATION_90
                180 -> FirebaseVisionImageMetadata.ROTATION_180
                270 -> FirebaseVisionImageMetadata.ROTATION_270
                else -> FirebaseVisionImageMetadata.ROTATION_0
            }

            val metadata = FirebaseVisionImageMetadata.Builder()
                .setFormat(FirebaseVisionImageMetadata.IMAGE_FORMAT_YV12)
                .setHeight(image.height)
                .setWidth(image.width)
                .setRotation(result)
                .build()

            val labelImage = FirebaseVisionImage.fromByteArray(data, metadata)

            FirebaseVision.getInstance().getOnDeviceImageLabeler()
                .processImage(labelImage)
                .addOnSuccessListener {
                    if (it.isNotEmpty()) {
                        tv.text = it[0].text + "  " + it[0].confidence
                    }
                }
        }

    }
}
