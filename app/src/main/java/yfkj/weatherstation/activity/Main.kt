package yfkj.weatherstation.activity

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.PowerManager
import android.text.TextUtils
import android.view.KeyEvent
import android.view.ViewGroup
import android.widget.EditText
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import com.alibaba.fastjson.TypeReference
import com.allenliu.versionchecklib.callback.APKDownloadListener
import com.allenliu.versionchecklib.v2.AllenVersionChecker
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder
import com.allenliu.versionchecklib.v2.builder.NotificationBuilder
import com.allenliu.versionchecklib.v2.builder.UIData
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.pawegio.kandroid.runDelayed
import com.pawegio.kandroid.runDelayedOnUiThread
import kotlinx.android.synthetic.main.f1_r3.*
import kotlinx.android.synthetic.main.fragment1.*
import kotlinx.android.synthetic.main.main.*
import org.eclipse.paho.client.mqttv3.*
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence
import yfkj.weatherstation.R
import yfkj.weatherstation.adapter.LazyFragmentAdapter
import yfkj.weatherstation.db.Content
import yfkj.weatherstation.db.MqttBean
import yfkj.weatherstation.db.StationLocation
import yfkj.weatherstation.db.upDown
import yfkj.weatherstation.fragment.*
import yfkj.weatherstation.utils.*
import yfkj.weatherstation.view.NoScrollViewPager
import yfkj.weatherstation.utils.ActivityUtils
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread
import kotlin.system.exitProcess

class Main : AppCompatActivity(), AMapLocationListener,
    Fragment1.UpF1Listenner, Fragment3.UpF3Listenner,
    Fragment4.UpF4Listenner {
    var tvId = "点击登录"
    val fragments: ArrayList<Fragment> = ArrayList()
    var viewPager: NoScrollViewPager? = null
    var mMqClint: MqttClient? = null
    var utils: ActivityUtils? = null
    var mMqttConnectOptions: MqttConnectOptions? = null
    var upTopic: MqttTopic? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        utils = ActivityUtils(this)
        fragments.add(Fragment1())
        fragments.add(Fragment2())
        fragments.add(Fragment3())
        fragments.add(Fragment4())
        fragments.add(Fragment5())
        fragments.add(Fragment6())
        val adapter =
            LazyFragmentAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        adapter.items = fragments
        viewPager = view_pager
        view_pager!!.adapter = adapter
        tab1.setOnClickListener {
            bac()
            tab1.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.icon1))
            view_pager.currentItem = 0
            val fragment1 = fragments[0] as Fragment1
            fragment1.stationId.text = "编号:$tvId"
        }
        tab2.setOnClickListener {
            bac()
            tab2.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.icon2))
            view_pager.currentItem = 1
            val fragment2 = fragments[1] as Fragment2
            runDelayed(1000) {
                fragment2.up()
            }
        }
        tab3.setOnClickListener {
            bac()
            tab3.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.icon3))
            view_pager.currentItem = 2
            runDelayed(1000) {
                val stationName = UserInfo.name
                val address = UserInfo.address
                val fragment3 = fragments[2] as Fragment3
                fragment3.etName!!.setText(stationName!!.toCharArray(), 0, stationName.length)
                fragment3.etName!!.setSelection(fragment3.etName!!.text.length)
                fragment3.etAddress!!.setText(address!!.toCharArray(), 0, address.length)
            }
        }
        tab4.setOnClickListener {
            bac()
            tab4.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.icon4))
            view_pager.currentItem = 3
            val fragment4 = fragments[3] as Fragment4
            runDelayed(1000) {
                if (fragment4.isShow()) {
                    val kwUp = UserInfo.wdUp
                    val kwDown = UserInfo.wdDown
                    val ksUp = UserInfo.sdUp
                    val ksDown = UserInfo.sdDown
                    val twUp = UserInfo.twUp
                    val twDown = UserInfo.twDown
                    val tsUp = UserInfo.tsUp
                    val tsDown = UserInfo.tsDown
                    val gzUp = UserInfo.gzUp
                    val gzDown = UserInfo.gzDown
                    val qyUp = UserInfo.qyUp
                    val qyDown = UserInfo.qyDown
                    val ylUp = UserInfo.ylUp
                    val ylDown = UserInfo.ylDown
                    val fsUp = UserInfo.fsUp
                    val fsDown = UserInfo.fsDown
                    val dlDown1 = UserInfo.dlDown
                    var mkTime = UserInfo.time
                    if (mkTime < 5) {
                        mkTime = 5
                    }
                    fragment4.wdUp!!.setText(kwUp!!.toCharArray(), 0, kwUp.length)
                    fragment4.wdUp!!.setSelection(kwUp.length)
                    fragment4.wdDown!!.setText(kwDown!!.toCharArray(), 0, kwDown.length)
                    fragment4.sdUp!!.setText(ksUp!!.toCharArray(), 0, ksUp.length)
                    fragment4.sdDown!!.setText(ksDown!!.toCharArray(), 0, ksDown.length)
                    fragment4.twdUp!!.setText(twUp!!.toCharArray(), 0, twUp.length)
                    fragment4.twdDown!!.setText(twDown!!.toCharArray(), 0, twDown.length)
                    fragment4.tsdUp!!.setText(tsUp!!.toCharArray(), 0, tsUp.length)
                    fragment4.tsdDown!!.setText(tsDown!!.toCharArray(), 0, tsDown.length)
                    fragment4.gzUp1!!.setText(gzUp!!.toCharArray(), 0, gzUp.length)
                    fragment4.gzDown1!!.setText(gzDown!!.toCharArray(), 0, gzDown.length)
                    fragment4.qyUp1!!.setText(qyUp!!.toCharArray(), 0, qyUp.length)
                    fragment4.qyDown1!!.setText(qyDown!!.toCharArray(), 0, qyDown.length)
                    fragment4.ylUp1!!.setText(ylUp!!.toCharArray(), 0, ylUp.length)
                    fragment4.ylDown1!!.setText(ylDown!!.toCharArray(), 0, ylDown.length)
                    fragment4.fsUp1!!.setText(fsUp!!.toCharArray(), 0, fsUp.length)
                    fragment4.fsDown1!!.setText(fsDown!!.toCharArray(), 0, fsDown.length)
                    fragment4.dlDown!!.setText(dlDown1!!.toCharArray(), 0, dlDown1.length)
                    fragment4.etTime!!.setText(
                        mkTime.toString().toCharArray(), 0,
                        mkTime.toString().length
                    )
                }
            }
        }
        tab5.setOnClickListener {
            bac()
            tab5.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.icon5))
            view_pager.currentItem = 4
            val fragment5 = fragments[4] as Fragment5
            runDelayed(1000) {
                fragment5.setData()
            }
        }
        tab6.setOnClickListener {
            bac()
            tab6.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.icon6))
            view_pager.currentItem = 5
            val fragment6 = fragments[5] as Fragment6
            runDelayed(1000) {
                fragment6.up()
            }
        }
        runDelayed(2000) {
            val fragment3 = fragments[2] as Fragment3
            fragment3.setF3Listenner(this)
            val fragment4 = fragments[3] as Fragment4
            fragment4.setF4Listenner(this)
            val fragment1 = fragments[0] as Fragment1
            fragment1.setF1Listenner(this)
            fragment1.stationId.setOnClickListener {
                if (!MyNetUtils.isNetworkConnected(this@Main)) {
                    runOnUiThread {
                        utils!!.showToast("请链接网络")
                    }
                    return@setOnClickListener
                }
                val readId = FileUtils.ReadDataFromStorage("", "", 2).trim()
                if (!TextUtils.isEmpty(readId)) {
                    tvId = readId
                    ThreadTest2().start()
                    runDelayed(3000) {
                        initMqtt()
                    }
                } else {
                    showPopup()
                }
            }
        }
        conMQTT("")
    }

    private var mlocationClient: AMapLocationClient? = null
    private var mLocationOption: AMapLocationClientOption? = null

    private fun bac() {
        tab1.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.icons1))
        tab2.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.icons2))
        tab3.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.icons3))
        tab4.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.icons4))
        tab5.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.icons5))
        tab6.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.icons6))
    }

    override fun send(str: String) {
        sendMsg(str)
    }

    override fun send3() {
        backName()
    }

    override fun send4() {
        backUp()
    }
    @SuppressLint("MissingPermission", "HardwareIds")
    private var mWakelock: PowerManager.WakeLock? = null
    @SuppressLint("InvalidWakeLockTag", "WakelockTimeout")
    private fun sendMsg(msg: String) {
        if (mMqClint != null && mMqClint!!.isConnected) {
            if (!TextUtils.isEmpty(msg)) {
                val message = MqttMessage()
                message.payload = msg.toByteArray()
                try {
                    upTopic!!.publish(message)
                } catch (e: MqttException) {
                    e.printStackTrace()
                }
            }
        } else {
            conMQTT("")
        }
    }

    private fun backName() {
        val name = UserInfo.name
        val address = UserInfo.address
        val id = UserInfo.id
        val lat = UserInfo.lat
        val lng = UserInfo.lng
        val nameBean = Content()
        nameBean.stationAddress = address
        nameBean.stationCode = id
        nameBean.type = "2"
        nameBean.stationName = name
        val stationLocation = StationLocation()
        stationLocation.lng = "" + lng
        stationLocation.lat = "" + lat
        nameBean.stationLocation = stationLocation
        sendMsg(JSON.toJSONString(nameBean))
    }

    var upList = arrayListOf<upDown.AlarmDataBean>()
    private fun backUp() {
        upList.clear()
        val alarm = upDown.AlarmDataBean()
        alarm.acqCode = "WD"
        alarm.highAlarmValue = UserInfo.wdUp
        alarm.lowAlarmValue = UserInfo.wdDown
        upList.add(alarm)

        val alarm1 = upDown.AlarmDataBean()
        alarm1.acqCode = "SD"
        alarm1.highAlarmValue = UserInfo.sdUp
        alarm1.lowAlarmValue = UserInfo.sdDown
        upList.add(alarm1)

        val alarm2 = upDown.AlarmDataBean()
        alarm2.acqCode = "TRWD"
        alarm2.highAlarmValue = UserInfo.twUp
        alarm2.lowAlarmValue = UserInfo.twDown
        upList.add(alarm2)

        val alarm3 = upDown.AlarmDataBean()
        alarm3.acqCode = "TRSD"
        alarm3.highAlarmValue = UserInfo.tsUp
        alarm3.lowAlarmValue = UserInfo.twDown
        upList.add(alarm3)

        val alarm4 = upDown.AlarmDataBean()
        alarm4.acqCode = "GZQD"
        alarm4.highAlarmValue = UserInfo.gzUp
        alarm4.lowAlarmValue = UserInfo.gzDown
        upList.add(alarm4)

        val alarm5 = upDown.AlarmDataBean()
        alarm5.acqCode = "DQYL"
        alarm5.highAlarmValue = UserInfo.qyUp
        alarm5.lowAlarmValue = UserInfo.qyDown
        upList.add(alarm5)

        val alarm6 = upDown.AlarmDataBean()
        alarm6.acqCode = "JYL"
        alarm6.highAlarmValue = UserInfo.ylUp
        alarm6.lowAlarmValue = UserInfo.ylDown
        upList.add(alarm6)

        val alarm7 = upDown.AlarmDataBean()
        alarm7.acqCode = "FS"
        alarm7.highAlarmValue = UserInfo.fsUp
        alarm7.lowAlarmValue = UserInfo.fsDown
        upList.add(alarm7)

        val alarm8 = upDown.AlarmDataBean()
        alarm8.acqCode = "SYDL"
        alarm8.highAlarmValue = ""
        alarm8.lowAlarmValue = UserInfo.dlDown
        upList.add(alarm8)
        val id = UserInfo.id
        val upDown = upDown()
        upDown.type = "3"
        upDown.acqInterval = UserInfo.time.toString()
        upDown.alarmData = upList
        upDown.stationCode = id
        sendMsg(JSON.toJSONString(upDown))
        utils!!.showToast("正在重启配置...")
        val fragment1 = fragments[0] as Fragment1
        fragment1.send1()
    }

    @SuppressLint("SimpleDateFormat")
    override fun onLocationChanged(amapLocation: AMapLocation) {
        if (amapLocation != null) {
            if (amapLocation.errorCode == 0) {
                //定位成功回调信息，设置相关消息
                val lat = amapLocation.latitude //获取纬度
                val lng = amapLocation.longitude //获取经度
                UserInfo.lat = lat.toFloat()
                UserInfo.lng = lng.toFloat()
                mlocationClient!!.stopLocation()
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            utils!!.showToast("返回键无效")
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    var topicDown = ""
    var topicUp = ""
    var client = ""
    var tvName = ""
    var tvPass = ""
    var ip = ""
    private fun showPopup() {
        val view = utils!!.getView(this, R.layout.pop_login)
        val popup = PopupWindow(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )
        popup.contentView = view
        popup.setBackgroundDrawable(ColorDrawable(0x00000000))
        popup.isClippingEnabled = false
        popup.isFocusable = true
        popup.showAsDropDown(view)
        val id = view.findViewById<EditText>(R.id.et_id)
        val sure = view.findViewById<TextView>(R.id.sure)
        sure.setOnClickListener {
            val trim = id.text.toString().trim()
            if (TextUtils.isEmpty(trim)) {
                utils!!.showToast("请输入设备ID")
                return@setOnClickListener
            }
            popup.dismiss()
            conMQTT(trim)
        }
    }

    @SuppressLint("InvalidWakeLockTag", "WakelockTimeout")
    private fun conMQTT(id: String) {
        if (!MyNetUtils.isNetworkConnected(this@Main)) {
            runOnUiThread {
                //亮屏
                val pm = getSystemService(POWER_SERVICE) as PowerManager
                mWakelock = pm.newWakeLock(
                    PowerManager.ACQUIRE_CAUSES_WAKEUP or
                            PowerManager.SCREEN_DIM_WAKE_LOCK, "target"
                )
                mWakelock!!.acquire()
                mWakelock!!.release()
                utils!!.showToast("正在重新连接网络...")
            }
            return
        }
        runOnUiThread {
            //亮屏
            val pm = getSystemService(POWER_SERVICE) as PowerManager
            mWakelock = pm.newWakeLock(
                PowerManager.ACQUIRE_CAUSES_WAKEUP or
                        PowerManager.SCREEN_DIM_WAKE_LOCK, "target"
            )
            mWakelock!!.acquire()
            mWakelock!!.release()
            utils!!.showToast("正在重新连接服务...")
        }
        if (!TextUtils.isEmpty(id)) {
            tvId = id
            ThreadTest2().start()
            runDelayed(3000) {
                initMqtt()
            }
        } else {
            val readId = FileUtils.ReadDataFromStorage("", "", 2).trim()
            if(TextUtils.isEmpty(readId)){
                runOnUiThread { utils!!.showToast("本地账号为空") }
                return
            }
            val split = readId.split("*")
            tvId = split[0]
            client = split[1]
            tvName = split[2]
            tvPass = split[3]
            topicDown = split[4]
            topicUp =split[5]
            ip = split[6]
            initMqtt()
        }
    }

    inner class ThreadTest2 : Thread() {
        override fun run() {
            var url: URL? = null
            //http://39.100.50.234:8080/ 泌阳
            //http://nywlw.hnyfkj.com
            //http://120.220.207.158:8087/app/weather/21011301.properties 庆云
            try {
                url = URL("http://nywlw.hnyfkj.com/app/weather/$tvId.properties")
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            }
            var openConnection: HttpURLConnection? = null
            try {
                openConnection = url!!.openConnection() as HttpURLConnection
            } catch (e: IOException) {
                e.printStackTrace()
            }
            var inputStream: InputStream? = null
            try {
                inputStream = openConnection!!.inputStream
            } catch (e: IOException) {
                e.printStackTrace()
            }
            val properties = Properties()
            try {
                properties.load(inputStream)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            client = properties["clientId"].toString().trim()
            tvName = properties["username"].toString().trim()
            tvPass = properties["password"].toString().trim()
            topicDown = properties["topicDown"].toString().trim()
            topicUp = properties["topicUp"].toString().trim()
            ip = properties["mqttAddr"].toString().trim()

        }
    }

    private fun initMqtt() {
        if (TextUtils.isEmpty(ip)) {
            runOnUiThread { utils!!.showToast("请输入正确的设备ID登录") }
            return
        }
        val readId = FileUtils.ReadDataFromStorage("", "", 2).trim()
        if (TextUtils.isEmpty(readId)) {
            FileUtils.WriteDataToStorage("$tvId*$client*$tvName*$tvPass*$topicDown*$topicUp*$ip",
                "", "", 3, 0)
        }
        try {
            mMqClint = MqttClient(ip, client, MemoryPersistence())
            mMqttConnectOptions = MqttConnectOptions()
            //清除缓存
            mMqttConnectOptions!!.isCleanSession = true
            //设置用户名
            mMqttConnectOptions!!.userName = tvName
            //设置用户密码
            mMqttConnectOptions!!.password = tvPass.toCharArray()
            // 设置超时时间，单位：秒
            mMqttConnectOptions!!.connectionTimeout = 10
            // 心跳包发送间隔，单位：秒
            mMqttConnectOptions!!.keepAliveInterval = 60
            //设置回调
            mMqClint!!.setCallback(PushCallBack())
            // MqttTopic topic = client.getTopic(ConnectUrl.SUBSCRIBETOPIC)
            mMqttConnectOptions!!.setWill(topicDown, "open".toByteArray(), 0, true)
            //订阅消息
            connect()
        } catch (e: MqttException) {
            e.printStackTrace()

        }
    }

    /**
     * mqtt链接
     */
    @SuppressLint("SetTextI18n")
    private fun connect() {
        try {
            if (mMqClint != null) {
                //开始链接
                mMqClint!!.connect(mMqttConnectOptions)
                upTopic = mMqClint!!.getTopic(topicUp)
                UserInfo.id = tvId
                runDelayedOnUiThread(2000){
                    Fragment1.tvStationId!!.text = "编号:$tvId"
                }
            }
        } catch (e: Exception) {
            utils!!.showToast("登录失败:$e")
            e.printStackTrace()
        }
        runDelayed(1500){
            loca()
        }
    }

    private fun loca(){
        if(Fragment1.tvStationId!!.text!="点击登录") {
            //高德
            mlocationClient = AMapLocationClient(this)
            mLocationOption = AMapLocationClientOption()
            mlocationClient!!.setLocationListener(this)
            //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
            mLocationOption!!.locationMode =
                AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
            mLocationOption!!.isOnceLocation = true
            mlocationClient!!.setLocationOption(mLocationOption)
            mlocationClient!!.startLocation()

            //发送位置
            val lat = UserInfo.lat
            val lng = UserInfo.lng
            val id = UserInfo.id
            val nameBean = Content()
            nameBean.stationAddress = null
            nameBean.stationCode = id
            nameBean.type = "2"
            nameBean.stationName = null
            val stationLocation = StationLocation()
            stationLocation.lng = "" + lng
            stationLocation.lat = "" + lat
            nameBean.stationLocation = stationLocation
            sendMsg(JSON.toJSONString(nameBean))

        }else{
            runDelayed(5500){
                loca()
            }
        }
    }

    inner class PushCallBack : MqttCallback {
        override fun connectionLost(throwable: Throwable?) {
            connect()
        }

        @SuppressLint("InvalidWakeLockTag", "WakelockTimeout")
        override fun messageArrived(topic: String?, message: MqttMessage?) {
            //  LogUtils.i("messageArrived: 接收消息回调$message")
            val bean =
                JSONObject.parseObject(
                    message.toString(),
                    object : TypeReference<MqttBean>() {})
            val deId = UserInfo.id
            when (bean.name) {
                //名称 地址 经纬度修改
                "2" -> {
                    val bean1 =
                        JSONObject.parseObject(
                            bean.content,
                            object : TypeReference<Content>() {})
                    if (bean1.stationCode == deId) {
                        runOnUiThread {
                            val stationName = bean1.stationName
                            UserInfo.name = stationName
                            UserInfo.address = bean1.stationAddress
                            Fragment1.tvName!!.text = stationName
                        }
                    }
                }
                //软件升级
                "0" -> {
                    val bean1 =
                        JSONObject.parseObject(
                            bean.content,
                            object : TypeReference<Content>() {})
                    if (bean1.stationCode == deId) {
                        popup(bean1.downloadUrl!!)
                    }
                }
                //上下限 时长修改
                "3" -> {
                    val bean1 =
                        JSONObject.parseObject(
                            bean.content,
                            object : TypeReference<upDown>() {})
                    if (bean1.stationCode == deId) {
                        UserInfo.time = bean1.acqInterval!!.toInt()
                        UserInfo.wdUp = bean1.alarmData?.get(0)!!.highAlarmValue
                        UserInfo.wdDown = bean1.alarmData?.get(0)!!.lowAlarmValue
                        UserInfo.sdUp = bean1.alarmData?.get(1)!!.highAlarmValue
                        UserInfo.sdDown = bean1.alarmData?.get(1)!!.lowAlarmValue
                        UserInfo.twUp = bean1.alarmData?.get(2)!!.highAlarmValue
                        UserInfo.twDown = bean1.alarmData?.get(2)!!.lowAlarmValue
                        UserInfo.tsUp = bean1.alarmData?.get(3)!!.highAlarmValue
                        UserInfo.tsDown = bean1.alarmData?.get(3)!!.lowAlarmValue
                        UserInfo.gzUp = bean1.alarmData?.get(4)!!.highAlarmValue
                        UserInfo.gzDown = bean1.alarmData?.get(4)!!.lowAlarmValue
                        UserInfo.qyUp = bean1.alarmData?.get(5)!!.highAlarmValue
                        UserInfo.qyDown = bean1.alarmData?.get(5)!!.lowAlarmValue
                        UserInfo.ylUp = bean1.alarmData?.get(6)!!.highAlarmValue
                        UserInfo.ylDown = bean1.alarmData?.get(6)!!.lowAlarmValue
                        UserInfo.fsUp = bean1.alarmData?.get(8)!!.highAlarmValue
                        UserInfo.fsDown = bean1.alarmData?.get(8)!!.lowAlarmValue
                        UserInfo.dlDown = bean1.alarmData?.get(9)!!.lowAlarmValue
                        runOnUiThread{
                            //亮屏
                            val pm = getSystemService(POWER_SERVICE) as PowerManager
                            mWakelock = pm.newWakeLock(
                                PowerManager.ACQUIRE_CAUSES_WAKEUP or
                                        PowerManager.SCREEN_DIM_WAKE_LOCK, "target"
                            )
                            mWakelock!!.acquire()
                            mWakelock!!.release()
                            utils!!.showToast("正在重启配置...")

                        }
                        val fragment1 = fragments[0] as Fragment1
                        fragment1.send1()
                    }
                }
                //硬件重启
                "4" -> {
                    val bean1 =
                        JSONObject.parseObject(
                            bean.content,
                            object : TypeReference<Content>() {})
                    if (bean1.stationCode == deId) {
                        val intent = Intent()
                        intent.action = "ACTION_RK_REBOOT"
                        sendBroadcast(intent, null)
                    }
                }
                "5" -> {
                    val bean1 =
                        JSONObject.parseObject(
                            bean.content,
                            object : TypeReference<Content>() {})
                    if (bean1.stationCode == deId) {
                        val intent = Intent()
                        intent.action = "ACTION_RK_REBOOT"
                        sendBroadcast(intent, null)
                    }
                }
            }
        }

        override fun deliveryComplete(token: IMqttDeliveryToken?) {
            //  LogUtils.i("deliveryComplete: 发布消息回调")
        }
    }

    //以下为版本升级
    private fun popup(str: String) {
        val builder: DownloadBuilder = AllenVersionChecker
            .getInstance()
            .downloadOnly(crateUIData(str))
        builder.notificationBuilder = NotificationBuilder.create()
            .setRingtone(true)
            .setIcon(R.mipmap.img5)
            .setTicker("版本：" + utils!!.version)
            .setContentTitle("程序升级")
            .setContentText(getString(R.string.app_name))
        builder.isDirectDownload = true
        builder.isShowNotification = true
        builder.isShowDownloadingDialog = true
        builder.isShowDownloadFailDialog = true
        builder.isForceRedownload = true
        builder.downloadAPKPath = "/storage/emulated/0/AllenVersionPath/"
        builder.apkName = "qxz"
        builder.apkDownloadListener = object : APKDownloadListener {
            override fun onDownloading(progress: Int) {
            }

            override fun onDownloadSuccess(file: File?) {
                val intent = Intent()
                intent.action = "ACTION_UPDATE_START"
                intent.putExtra("path", "/storage/emulated/0/AllenVersionPath/qxz.apk")
                sendBroadcast(intent, null)

                //应用过滤条件
                val mainIntent = Intent(Intent.ACTION_MAIN, null)
                mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
                val mPackageManager = packageManager
                val mAllApps = mPackageManager.queryIntentActivities(mainIntent, 0)
                for (res in mAllApps) {
                    //该应用的包名和主Activity
                    val pkg: String = res.activityInfo.packageName
                    val cls: String = res.activityInfo.name
                    // 打开
                    if (pkg.contains("start")) {
                        val componet = ComponentName(pkg, cls)
                        val intent6 = Intent()
                        intent6.component = componet
                        intent6.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent6)
                    }
                }
            }

            override fun onDownloadFail() {

            }
        }
        builder.setCustomDownloadInstallListener { context, apk ->

        }
        builder.executeMission(this)
    }

    private fun crateUIData(str: String): UIData? {
        val uiData = UIData.create()
        uiData.downloadUrl = str
        //"http://115.56.231.22:9003/hnyfkj-file-server/plugins/qxz.apk"
        return uiData
    }

    override fun onDestroy() {
        super.onDestroy()
        mlocationClient!!.onDestroy()
    }
}