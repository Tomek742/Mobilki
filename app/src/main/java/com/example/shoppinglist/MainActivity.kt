package com.example.shoppinglist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.shoppinglist.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var itemarraylist: ArrayList<Item>
    var imageId = intArrayOf()
    var amount = arrayOf<String>()
    var name = arrayOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val addButton = findViewById<FloatingActionButton>(R.id.AddButtom)
//        val checkButton = findViewById<CheckBox>(R.id.checkBox)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageId = intArrayOf(
            R.drawable.apples,
            R.drawable.pears
        )

        name = arrayOf(
            "apples",
            "pears"
        )

        amount = arrayOf(
            "2 kg",
            "6 pieces"
        )

        itemarraylist = ArrayList()

        for (i in name.indices){
            val item = Item(name[i],amount[i],imageId[i])
            itemarraylist.add(item)
        }

        binding.listView.isClickable = true
        binding.listView.adapter = MyAdapter(this, itemarraylist)
        binding.listView.setOnItemClickListener { parent, view, position, id ->

            val name = name[position]
            val amount = amount[position]
            val imageID = imageId[position]

            val i = Intent(this, ItemActivity:: class.java)
            i.putExtra("name", name)
            i.putExtra("amount", amount)
            i.putExtra("imageID", imageID)
            startActivity(i)

        }

        binding.AddButtom.setOnClickListener {
            val i = Intent(this, AddItemActivity:: class.java)
            startActivity(i)

        }

//        binding.AddButtom.isClickable = true
//        binding.listView.adapter = MyAdapter(this, itemarraylist)
//        binding.listView.setOnItemClickListener { parent, view, position, id ->
//
//
//            val i = Intent(this, AddItemActivity:: class.java)
//            startActivity(i)
//
//
//        }


//        addButton.setOnClickListener(){
//            val i = Intent(this, AddItemActivity:: class.java)
//            startActivity(i)
//        }





//        val listView = findViewById<ListView>(R.id.listView)
        //        var arrayList<String> items
//
//
//        val titile = findViewById<TextView>(R.id.TITLE)
//        val testButton = findViewById<Button>(R.id.button)
//        var crossed = false;
//        testButton.setOnClickListener() {
//            if (crossed) {
//                crossed = false
//                titile.setPaintFlags(titile.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG)
//            } else {
//                crossed = true
//                titile.setPaintFlags(titile.getPaintFlags() and STRIKE_THRU_TEXT_FLAG.inv())
//
//            }
//        }
//        val checkbox1 = findViewById<CheckBox>(R.id.checkBox1)
//        val textView1 = findViewById<TextView>(R.id.textView3)
//        val checkbox2 = findViewById<CheckBox>(R.id.checkBox2)
//        val checkbox3 = findViewById<CheckBox>(R.id.checkBox3)
//        val checkbox4 = findViewById<CheckBox>(R.id.checkBox4)
//        val checkbox5 = findViewById<CheckBox>(R.id.checkBox5)

//        checkbox1.setOnClickListener(){
//            if(checkbox1.isChecked)
//           checkbox1.apply {
////               paintFlags =  Paint.STRIKE_THRU_TEXT_FLAG
//               textView1.text ="text"
//               textView1.text
//           }
//            else {
//                checkbox1.apply {
////                    paintFlags = Paint.FAKE_BOLD_TEXT_FLAG
//
//                    paintFlags = Paint.Join
//                    STRIKE_THRU_TEXT_FLAG.inv()
//                    paintFlags = Paint.STRIKE_THRU_TEXT_FLAG.inv()
//                    paintFlags = Paint.FAKE_BOLD_TEXT_FLAG.inv()
////                    checkbox1.typeface = Typeface.DEFAULT
//                }
//            }
//        }
    }
}
