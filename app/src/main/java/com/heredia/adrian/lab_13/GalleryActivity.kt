package com.heredia.adrian.lab_13

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.heredia.adrian.lab_13.databinding.ActivityGalleryBinding
import java.io.File

class GalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ruta donde se guardan las fotos
        val outputDirectory = getOutputDirectory()
        val files = outputDirectory.listFiles { file ->
            file.extension == "jpg"
        }?.sortedByDescending { it.lastModified() }?.toTypedArray() ?: emptyArray()

        // Seteamos el adaptador con las fotos
        val adapter = GalleryAdapter(files)
        binding.viewPager.adapter = adapter
    }

    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, "CameraX-Images").apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists()) mediaDir else filesDir
    }
}