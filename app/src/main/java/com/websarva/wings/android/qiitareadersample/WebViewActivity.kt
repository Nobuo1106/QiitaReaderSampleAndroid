package com.websarva.wings.android.qiitareadersample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val webView: WebView = findViewById(R.id.web_view)
        val url = intent.getStringExtra("url")
        if (url != null) {
            webView.loadUrl(url)
        }
    }
}