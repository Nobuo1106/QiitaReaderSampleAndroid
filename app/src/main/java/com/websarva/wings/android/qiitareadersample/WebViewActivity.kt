package com.websarva.wings.android.qiitareadersample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import android.widget.TextView

class WebViewActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var closeButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val webView: WebView = findViewById(R.id.web_view)
        closeButton = findViewById<Button>(R.id.close_button) // update this line
        val url = intent.getStringExtra("url")
        if (url != null) {
            webView.loadUrl(url)
        }

        closeButton.setOnClickListener {
            finish()
        }
    }
}