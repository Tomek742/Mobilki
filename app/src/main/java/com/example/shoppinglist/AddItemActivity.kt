package com.example.shoppinglist

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.shoppinglist.databinding.ActivityItemBinding
import java.net.URI

class AddItemActivity : AppCompatActivity() {

    var imageID: Uri? = null

    private lateinit var binding: ActivityItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_add_item)
        var Name = ""
        var Amount = ""

        val photo = findViewById<ImageView>(R.id.PlaceImageHere)
        val bttnCamera = findViewById<ImageView>(R.id.imageView1)
        val bttnWeb = findViewById<ImageView>(R.id.imageView2)
        val bttnFile = findViewById<ImageView>(R.id.imageView3)
        val ItemName = findViewById<EditText>(R.id.editItemName)
        val ItemAmount = findViewById<EditText>(R.id.editItemAmount)
        val bttnAdd = findViewById<Button>(R.id.create)
        val bttnReturn = findViewById<Button>(R.id.back)


        var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                imageID = Uri.parse(data?.getStringExtra("ImageID"))
                println(imageID.toString())

                if (imageID != null) {
                    photo.setImageURI(imageID)
                }

            }
        }
        bttnCamera.setOnClickListener{
            if (ItemName.text != null){
                Name = ItemName.text.toString()
            }
            if (ItemAmount.text != null){
                Amount = ItemAmount.text.toString()
            }
            //TODO CAMERA
        }
        bttnWeb.setOnClickListener{
            if (ItemName.text != null){
                Name = ItemName.text.toString()
            }
            if (ItemAmount.text != null){
                Amount = ItemAmount.text.toString()
            }

            val i = Intent(this, WebActivity::class.java)
            resultLauncher.launch(i)
        }
        bttnFile.setOnClickListener{
            if (ItemName.text != null){
                Name = ItemName.text.toString()
            }
            if (ItemAmount.text != null){
                Amount = ItemAmount.text.toString()
            }

            val i = Intent(this, GalleryActivity::class.java)
            resultLauncher.launch(i)

        }

        bttnAdd.setOnClickListener{
            if (ItemName.text != null){
                Name = ItemName.text.toString()
            }
            if (ItemAmount.text != null){
                Amount = ItemAmount.text.toString()
            }
            if (Name != "" || Amount != "") {
                val i = Intent(this, MainActivity:: class.java)
                i.putExtra("name", Name)
                i.putExtra("amount", Amount)
                if (imageID == null) {
                    imageID = Uri.parse("android.resource://com.example.shoppinglist/"+ R.drawable.shop)
                }
//                val imageID = R.drawable.shop
                i.putExtra("ImageID", imageID.toString())
                setResult(Activity.RESULT_OK, i)
                super.onBackPressed()
//                startActivity(i)
            }
            else {
                Toast.makeText(this, "Please input name and amount", Toast.LENGTH_SHORT).show()
            }
        }
        bttnReturn.setOnClickListener{
            setResult(Activity.RESULT_CANCELED)
            super.onBackPressed()
        }
    }
}
