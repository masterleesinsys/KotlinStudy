package fly.kotlinstudy.cm.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebSettings
import fly.kotlinstudy.cm.R
import kotlinx.android.synthetic.main.activity_help.*

/**
 * 帮助
 */
class HelpActivity : BaseActivity() {
    //Kotlin官方中文文档
    var url : String="http://www.kotlindoc.cn/"

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        wv_help.loadUrl(url)
        val settings = wv_help.settings
        //支持javascript
        settings.javaScriptEnabled = true
        // 设置可以支持缩放
        settings.setSupportZoom(true)
        // 设置出现缩放工具
        settings.builtInZoomControls = true
        //扩大比例的缩放
        settings.useWideViewPort = true
        //自适应屏幕
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        settings.loadWithOverviewMode = true
    }
}
