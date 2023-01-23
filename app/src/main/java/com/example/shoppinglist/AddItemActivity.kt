package com.example.shoppinglist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.shoppinglist.databinding.ActivityItemBinding

class AddItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_add_item)
        var Name = ""
        var Amount = ""

        val bttnCamera = findViewById<ImageView>(R.id.imageView1)
        val bttnWeb = findViewById<ImageView>(R.id.imageView2)
        val bttnFile = findViewById<ImageView>(R.id.imageView3)
        val ItemName = findViewById<EditText>(R.id.editItemName)
        val ItemAmount = findViewById<EditText>(R.id.editItemAmount)
        val bttnAdd = findViewById<Button>(R.id.create)

        bttnCamera.setOnClickListener{
            if (ItemName.text != null){
                Name = ItemName.text.toString()
            }
            if (ItemAmount.text != null){
                Amount = ItemAmount.text.toString()
            }
            //TODO access to camera
        }
        bttnWeb.setOnClickListener{
            if (ItemName.text != null){
                Name = ItemName.text.toString()
            }
            if (ItemAmount.text != null){
                Amount = ItemAmount.text.toString()
            }

            //TODO search in web
        }
        bttnFile.setOnClickListener{
            if (ItemName.text != null){
                Name = ItemName.text.toString()
            }
            if (ItemAmount.text != null){
                Amount = ItemAmount.text.toString()
            }
            //TODO find file on phone
        }

        bttnAdd.setOnClickListener{
            if (ItemName.text != null){
                Name = ItemName.text.toString()
            }
            if (ItemAmount.text != null){
                Amount = ItemAmount.text.toString()
            }
            val i = Intent(this, ItemActivity:: class.java)
            i.putExtra("name", Name)
            i.putExtra("amount", Amount)
//            i.putExtra("imageID", imageID)
            startActivity(i)

        }
    }
}