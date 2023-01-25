package com.example.shoppinglist

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.shoppinglist.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var itemarraylist: ArrayList<Item>

    var imageId = arrayOf<Uri>(
        Uri.parse("android.resource://com.example.shoppinglist/"+ R.drawable.apples),
        Uri.parse("android.resource://com.example.shoppinglist/"+ R.drawable.pears),
        Uri.parse("android.resource://com.example.shoppinglist/"+ R.drawable.shop),
        Uri.parse("android.resource://com.example.shoppinglist/"+ R.drawable.shop)
    )

    var amount = arrayOf<String>(
        "2 kg",
        "6 pieces",
        "single",
        "once")

    var name = arrayOf<String>(
        "apples",
        "pears",
        "shop",
        "main"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val nameN: String? = data?.getStringExtra("name")
                val amountN: String? = data?.getStringExtra("amount")
                val imageIDN: Uri? = Uri.parse(data?.getStringExtra("ImageID"))
                println("Main1")
                println(imageIDN.toString())

                var imageIdClone = imageId.copyOf(imageId.size+1)
                var amountClone = amount.copyOf(amount.size+1)
                var nameClone = name.copyOf(name.size+1)

//                imageIdClone = imageId.clone()
//                amountClone = amount.clone()
//                nameClone = name.clone()

                nameClone[name.lastIndex + 1] = nameN.toString()
                amountClone[amount.lastIndex + 1] = amountN.toString()
                imageIdClone[imageId.lastIndex + 1] = imageIDN

                println("Main2")
                println(imageIDN.toString())


                name = nameClone.filterNotNull().toTypedArray()
                amount = amountClone.filterNotNull().toTypedArray()
                imageId = imageIdClone.filterNotNull().toTypedArray()

//                imageID[imageID.size+1] = imageIDN.toString()
            }
            if (result.resultCode == 5) {
                var index: Int? = null
                val data: Intent? = result.data
                val nameN: String? = data?.getStringExtra("name")
//                val amountN: String? = data?.getStringExtra("amount")
//                val imageIDN: Int = data?.getIntExtra("imageID",R.drawable.shop)!!
                for ((k, i) in name.withIndex()) {
                    if (i == nameN) {
                        index = k
                    }
                }
                if (index != null) {
                    imageId = remove(imageId, index)
                }
                if (index != null) {
                    name = remove(name,index)
                }
                if (index != null) {
                    amount = remove(amount,index)
                }
//                update()
            }
            binding.listView.adapter = MyAdapter(this, itemarraylist)
        }

        val addButton = findViewById<FloatingActionButton>(R.id.AddButtom)
//        val checkButton = findViewById<CheckBox>(R.id.checkBox)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


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
            i.putExtra("imageID", imageID.toString())
            resultLauncher.launch(i)
//            startActivity(i)

        }

        binding.AddButtom.setOnClickListener {
            val i = Intent(this, AddItemActivity:: class.java)
            resultLauncher.launch(i)
//            update()
//            startActivityForResult(i, 100)
//            startActivity(i)

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

    private fun remove(a: Array<Uri>, index: Int): Array<Uri> {
        if (index < 0 || index >= a.size) {
//            println("First if")
            return a
        }
        val result = arrayOfNulls<Uri>(a.size - 1)
        for (i in 0 until index) {
//            println("for loop 1")
            result[i] = Uri.parse(a[i].toString())
//            println(result[i])
        }
        for (i in index until a.size - 1) {
//            println("for loop 2")
            result[i] = Uri.parse(a[i + 1].toString())
//            println(result[i])
        }
        return result.filterNotNull().toTypedArray()
    }

    override fun onResume() {
        itemarraylist.clear()
        for (i in name.indices){
            val item = Item(name[i],amount[i],imageId[i])
            itemarraylist.add(item)
        }
        super.onResume()
    }


    private fun update() {
        itemarraylist.clear()
        for (i in name.indices){
            val item = Item(name[i],amount[i],imageId[i])
            itemarraylist.add(item)
        }
        super.onNewIntent(intent)
    }

    private fun remove(a: IntArray, index: Int): IntArray {
        if (index < 0 || index >= a.size) {
            return a
        }
        val result = IntArray(a.size - 1)
        for (i in 0 until index) {
            result[i] = a[i]
        }
        for (i in index until a.size - 1) {
            result[i] = a[i + 1]
        }
        return result
        }

    private fun remove(a: Array<String>, index: Int): Array<String> {
//        println("Remove called")      PRO DEBUGGING
        if (index < 0 || index >= a.size) {
//            println("First if")
            return a
        }
        val result = arrayOfNulls<String>(a.size - 1)
        for (i in 0 until index) {
//            println("for loop 1")
            result[i] = a[i]
//            println(result[i])
        }
        for (i in index until a.size - 1) {
//            println("for loop 2")
            result[i] = a[i + 1]
//            println(result[i])
        }
        return result.filterNotNull().toTypedArray()
    }


}
