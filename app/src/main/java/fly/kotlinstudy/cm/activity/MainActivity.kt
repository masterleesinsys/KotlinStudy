package fly.kotlinstudy.cm.activity

import android.os.Bundle
import fly.kotlinstudy.cm.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.toast

/**
 * 主页
 */
@Suppress("RedundantOverride")
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initEvent()
    }

    private fun initView() {
        flock.text = "QQ群: 668524118\n欢迎热爱生活，喜欢创新，有想法的朋友加入\n我们一起成长！"
    }

    private fun initEvent() {
        descriptionOfLanguageAdvantage.onClick { openActivity(LanguageAdvantageActivity::class.java) }
        initialConfigurationInstructions.onClick { toast("${initialConfigurationInstructions.text}!") }
        basicControlsAreIntroducedForUse.onClick { toast("${basicControlsAreIntroducedForUse.text}!") }
        advancedControlsAreIntroducedForUse.onClick { toast("${advancedControlsAreIntroducedForUse.text}!") }
        basicFunctionsAreIntroduced.onClick { toast("${basicFunctionsAreIntroduced.text}!") }
        advancedFunctionsAreIntroducedForUse.onClick { toast("${advancedFunctionsAreIntroducedForUse.text}!") }
        flock.onClick { toast("${flock.text}!") }
        share.onClick { toast("${share.text}!") }
        tv_back.onClick { finish() }
        tv_help.onClick { openActivity(HelpActivity::class.java) }
    }
}
