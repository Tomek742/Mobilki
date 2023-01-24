package com.example.shoppinglist

import android.app.Activity
import android.app.DownloadManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.webkit.DownloadListener
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class WebActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_web)
    val webButton = findViewById<Button>(R.id.saveweb)
    val myWebView: WebView = findViewById(R.id.webview)
    myWebView.webViewClient = WebViewClient()
    myWebView.loadUrl("https://www.google.com/imghp?hl=EN")

        webButton.setOnClickListener{
//        myWebView.

            val i = Intent(this, AddItemActivity:: class.java)
//            i.putExtra("imageID", imageID)
            setResult(25, i)

            super.onBackPressed()
        }
    }
}