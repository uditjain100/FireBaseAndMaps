package udit.programmer.co.firebase.FireBase

import android.annotation.SuppressLint
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web.*
import udit.programmer.co.firebase.R
import java.lang.StringBuilder

class WebActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        web_layout.loadUrl("https://www.google.com")
        web_layout.settings.javaScriptEnabled = true
        web_layout.canGoBack()
        web_layout.webViewClient = WebClient(this)

        search_btn.setOnClickListener {
            var url = StringBuilder(input_layout.text.toString()).toString()
            url = "https://$url"
            web_layout.loadUrl(url)
        }

        back_button.setOnClickListener {
            web_layout.goBack()
        }

    }

    class WebClient(private val activity: Activity) : WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            view?.loadUrl(request?.url.toString())
            return true
        }
    }
}