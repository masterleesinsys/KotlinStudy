package fly.kotlinstudy.cm.activity

import android.os.Bundle
import fly.kotlinstudy.cm.R
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.onClick

/**
 * 登录
 */
@Suppress("UNREACHABLE_CODE", "RedundantOverride")
class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login.onClick { openActivity(MainActivity::class.java) }
    }
}
