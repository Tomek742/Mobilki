package com.example.shoppinglist

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.media.Image
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CameraActivity : AppCompatActivity(){

    private val IMAGE_CAPTURE_CODE = 1001
    private val PERMISSION_CODE = 1000
    var imageUri: Uri? = null
    var photo: ImageView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        val captureBttn = findViewById<Button>(R.id.capture_btn)
        val returnBttn = findViewById<Button>(R.id.goBackToAddItem)
        photo = findViewById<ImageView>(R.id.photo)

        captureBttn.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED ||checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                    val permission = arrayOf(Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    requestPermissions(permission, PERMISSION_CODE)
                }
                else{
                    openCamera()
                }
            }
        }

        returnBttn.setOnClickListener{
            val i = Intent(this, AddItemActivity:: class.java)
            if (imageUri != null) {
                i.putExtra("ImageID", imageUri.toString())
                setResult(Activity.RESULT_OK, i)
            }
            else {
                setResult(Activity.RESULT_CANCELED,i)
            }
            super.onBackPressed()
        }
    }

    private fun openCamera() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Pictue")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From The camera")
        imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            PERMISSION_CODE -> {
                if ((grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    openCamera()
                }
                else{
                    Toast.makeText(this,"Permission denided", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            photo?.setImageURI(imageUri)
        }
    }
}