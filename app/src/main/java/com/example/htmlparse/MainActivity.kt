package com.example.htmlparse

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import java.net.URLConnection
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {
    @SuppressLint("JavascriptInterface")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var webView = findViewById<WebView>(R.id.webView)
        val settings: WebSettings = webView.settings
        settings.javaScriptEnabled = true
        val url = "YOUR_URL"

          webView.loadUrl(url)
        thread {
            getHtml(url)
        }
    }

    @Throws(IOException::class)
    fun getHtml(url: String) {
        // Build and set timeout values for the request.
        val connection: URLConnection = URL(url).openConnection()
        connection.connectTimeout = 5000
        connection.readTimeout = 5000
        connection.connect()
        val `in`: InputStream = connection.getInputStream()
        val reader = BufferedReader(InputStreamReader(`in`))
        Log.e("HTML", reader.readText())
        `in`.close()
    }
}