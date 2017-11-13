package fly.kotlinstudy.cm.activity

import android.os.Bundle
import fly.kotlinstudy.cm.R
import kotlinx.android.synthetic.main.activity_language_advantage.*
import org.jetbrains.anko.onClick

/**
 * 语言优势说明
 */
class LanguageAdvantageActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language_advantage)

        initView()
        initEvent()
    }

    private fun initView() {

    }

    private fun initEvent() {
        tv_back.onClick { finish() }
        tv_help.onClick { openActivity(HelpActivity::class.java) }
    }
}
