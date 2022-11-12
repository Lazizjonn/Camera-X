package uz.gita.camerax

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.camerax.databinding.ActivityMainBinding
import uz.gita.camerax.util.checkPermissions
import java.util.concurrent.Executor

class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            checkPermissions(arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                startCamera()
            }
        } else {
            checkPermissions(arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)) {
                startCamera()
            }
        }
    }

    private fun startCamera() {
        //  This is used to bind the lifecycle of cameras to the lifecycle owner
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        /*Add a listener to the cameraProviderFuture. Add a Runnable as one argument. We will fill it in later.
        Add ContextCompat.getMainExecutor() as the second argument.
        This returns an Executor that runs on the main thread.*/
        cameraProviderFuture.addListener({
            // This is used to bind the lifecycle of our camera to the LifecycleOwner within the application's process.
            val cameraProvider = cameraProviderFuture.get()

        }, {  })

    }
}