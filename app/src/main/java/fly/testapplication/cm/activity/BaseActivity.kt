package fly.testapplication.cm.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.*

class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_base)

        verticalLayout {
            padding = dip(30)
            editText {
                hint = "Name"
                textSize = 24f
            }
            editText {
                hint = "Password"
                textSize = 24f
            }
            button("Login") {
                textSize = 26f
                onClick { toast(button@ this.text) }
            }
        }
    }
}

