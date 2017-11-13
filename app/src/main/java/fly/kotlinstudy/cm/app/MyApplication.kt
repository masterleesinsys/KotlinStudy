package fly.kotlinstudy.cm.app

import android.app.Application
import org.xutils.x

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        x.Ext.init(this)
    }
}