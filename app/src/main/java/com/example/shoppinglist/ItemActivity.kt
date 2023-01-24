package com.example.shoppinglist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.shoppinglist.databinding.ActivityItemBinding

class ItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_item)

        val name = intent.getStringExtra("name")
        val amount = intent.getStringExtra("amount")
        val imageID = intent.getIntExtra("imageID",R.drawable.pears)

        val image = findViewById<ImageView>(R.id.itemImage)
        val itemName = findViewById<TextView>(R.id.itemName)
        val itemAmount = findViewById<TextView>(R.id.itemAmount)

        val bttnBack = findViewById<Button>(R.id.ButtonBack)
        val bttnRemove = findViewById<Button>(R.id.ButtonRemove)


//        binding.itemName.text = name
//        binding.itemAmount.text = amount
//        binding.itemImage.setImageResource(imageID)
//        itemName.text = name
        itemName.text = intent.getStringExtra("name")
//        itemAmount.text = amount
        itemAmount.text = intent.getStringExtra("amount")
        image.setImageResource(imageID)
//        binding.checkBox.isClickable = true
//        val textbox1 = findViewById<TextView>(

        bttnBack.setOnClickListener{
            super.onBackPressed()
        }

        bttnRemove.setOnClickListener{
        val i = Intent(this, MainActivity:: class.java)
        i.putExtra("name", name)
        i.putExtra("amount", amount)
        i.putExtra("imageID", imageID)
        setResult(5, i)

            //TODO remove then go to list

            super.onBackPressed()
        }
    }
}
