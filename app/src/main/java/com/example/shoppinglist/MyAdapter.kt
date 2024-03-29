package com.example.shoppinglist

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class MyAdapter(private val context: Activity, private val arrayList: ArrayList<Item>) : ArrayAdapter<Item>(context,R.layout.list_item,arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.list_item,null)

        val imageView: ImageView = view.findViewById(R.id.item_pic)
        val itemName: TextView = view.findViewById(R.id.itemName)
        val amount: TextView = view.findViewById(R.id.amount)

        imageView.setImageURI(arrayList[position].imageID)
        itemName.text = arrayList[position].name
        amount.text = arrayList[position].amount


        return view
    }
}