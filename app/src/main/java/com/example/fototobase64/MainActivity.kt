package com.example.fototobase64


import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    private val TAKE_PICTURE_REQUEST = 1
    lateinit var outputFileUri: Uri
    private val PERMISSION_REQUEST_CODE = 123
    val TAG = "MyLog"
    lateinit var recyclerView:RecyclerView
    val list = mutableListOf<Foto>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)
        permision()
        recyclerView = findViewById<RecyclerView>(R.id.RV)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(recyclerView.context, RecyclerView.VERTICAL, false)
            adapter = Adapter(list)
        }

        val str = "{\"имя\":\"test name\", \"возраст\":25}"
        val user = parceJson(str)
        Log.d(TAG, user.toString())
        val jsonString = toJsom(user)
        Log.d(TAG, jsonString)


        button.setOnClickListener {
            saveFullImage()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == TAKE_PICTURE_REQUEST && resultCode == RESULT_OK) {
            if (data?.hasExtra("data") == true) {
                val thumbnailBitmap = data.getParcelableExtra<Bitmap>("data")
            } else {
                val bitmap = uriToBitmap(outputFileUri, this)
                val str69 = getStringImage(bitmap)
                list.add(Foto(list.size.toLong(), str69))
                recyclerView.apply {
                    adapter?.notifyDataSetChanged()
                }

            }

        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun saveFullImage() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val outputFormat = SimpleDateFormat("dd_MM_yyyy_HH_mm_ss")
        val filename = outputFormat.format(Date())
        val file = File(this.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                "$filename.jpg")
        outputFileUri = FileProvider.getUriForFile(this, this.applicationContext.packageName + ".provider", file)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri)
        startActivityForResult(intent, TAKE_PICTURE_REQUEST)
    }

    fun permision() {
        val permissionStatus = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        if (permissionStatus != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    PERMISSION_REQUEST_CODE
            )
        }

    }

}


