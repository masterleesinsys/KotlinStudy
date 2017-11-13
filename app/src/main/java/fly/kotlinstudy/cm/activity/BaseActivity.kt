package fly.kotlinstudy.cm.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by Fly on 2017
 * Version 1.0.1
 * Mailbox 3256765723@qq.com
 * Blog m15896847719@163.com
 * QQ Group 668524118
 * Welcome friends who like innovation and love life, join us and grow up together!
 * End Thank you for your use.
 */
@Suppress("RedundantOverride")
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    protected fun openActivity(ActivityClass: Class<*>) {
        intent = Intent(this, ActivityClass)
        startActivity(intent)
    }

    protected fun openActivity(intent: Intent) {
        startActivity(intent)
    }
}