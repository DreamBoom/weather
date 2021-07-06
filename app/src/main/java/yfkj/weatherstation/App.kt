package yfkj.weatherstation

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Intent
import com.chibatching.kotpref.Kotpref
import com.raizlabs.android.dbflow.config.FlowManager
import org.xutils.x
import yfkj.weatherstation.utils.MyCatchException
import java.util.*


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Kotpref.init(this)
        x.Ext.init(this)
        context = applicationContext
        FlowManager.init(this)
        //异常捕获
        val mException = MyCatchException.getInstance()
        mException.init(applicationContext) //注册
    }


    companion object {
        private var myApplication: App? = null
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
            private set
        //最近定位时间

        /**
         * 单例模式中获取唯一的MyApplication实例
         *
         * @return
         */
        val instance: App
            get() {
                if (null == myApplication) {
                    myApplication = App()
                }

                return myApplication as App
            }
    }

    override fun onTerminate() {
        super.onTerminate()
    }

}