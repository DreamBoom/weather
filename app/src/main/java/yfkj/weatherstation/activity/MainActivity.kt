package yfkj.weatherstation.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PowerManager
import android.os.PowerManager.WakeLock
import androidx.appcompat.app.AppCompatActivity
import com.pawegio.kandroid.runDelayed
import com.pawegio.kandroid.startActivity
import yfkj.weatherstation.R


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val tManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
//        val location =
//            if (tManager.cellLocation == null) "未知地区" else tManager.cellLocation
//                .toString()
//
//        val simOperatorName =
//            if (tManager.simOperatorName == "") "未知" else tManager.deviceId.toString()
//
//        val networkOperatorName =
//            if (tManager.networkOperatorName == null) "未知" else tManager.networkOperatorName.toString()
//
//        val line1Number =
//            if (tManager.line1Number == null) "未知" else tManager.simSerialNumber.toString()
//
//        LogUtils.i("地区:$location 手机制式:$simOperatorName 运营商名称:$networkOperatorName SIM卡号:$line1Number")
        runDelayed(2000) {
            startActivity<Main>()
            finish()
        }
    }
}