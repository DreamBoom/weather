package yfkj.weatherstation.fragment

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.alibaba.fastjson.JSON
import com.pawegio.kandroid.runDelayedOnUiThread
import com.pawegio.kandroid.runOnUiThread
import com.raizlabs.android.dbflow.sql.language.Delete
import com.raizlabs.android.dbflow.sql.language.SQLite
import yfkj.weatherstation.R
import yfkj.weatherstation.adapter.LazyFragmentAdapter
import yfkj.weatherstation.db.*
import yfkj.weatherstation.serialportlibrary.Device
import yfkj.weatherstation.serialportlibrary.SerialPortFinder
import yfkj.weatherstation.serialportlibrary.SerialPortManager
import yfkj.weatherstation.serialportlibrary.listener.OnSerialPortDataListener
import yfkj.weatherstation.utils.*
import yfkj.weatherstation.view.CustomViewPager
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import kotlin.concurrent.thread

class Fragment1 : LazyFragment() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        var tvName: TextView? = null
        @SuppressLint("StaticFieldLeak")
        var tvStationId: TextView? = null
    }

    private val fragments: ArrayList<Fragment> = ArrayList()
    override fun getLayoutResource(): Int = R.layout.fragment1
    var tvWd: TextView? = null
    var tvSd: TextView? = null
    var tvTw: TextView? = null
    var tvTs: TextView? = null
    var tvGz: TextView? = null
    var tvQy: TextView? = null
    var tvYl: TextView? = null
    var tvFs: TextView? = null
    var tvFx: TextView? = null
    var tvZyl: TextView? = null
    var tvDl: TextView? = null
    var tvFzgl: TextView? = null
    var warmWd: TextView? = null
    var warmSd: TextView? = null
    var warmTw: TextView? = null
    var warmTs: TextView? = null
    var warmGz: TextView? = null
    var warmQy: TextView? = null
    var warmYl: TextView? = null
    var warmFs: TextView? = null
    var warmDl: TextView? = null

    var f1: TextView? = null
    var f2: TextView? = null
    var f3: TextView? = null
    var f4: TextView? = null
    var f5: TextView? = null
    var f6: TextView? = null
    var f7: TextView? = null
    var f8: TextView? = null
    var tvTime: TextView? = null

    @SuppressLint("SetTextI18n")
    override fun initView(root: View) {
        val bbh = root.findViewById<TextView>(R.id.bbh)
        bbh.text = "版本号:" + utils!!.version
        tvTime = root.findViewById(R.id.tvTime)
        f1 = root.findViewById(R.id.f1)
        f2 = root.findViewById(R.id.f2)
        f3 = root.findViewById(R.id.f3)
        f4 = root.findViewById(R.id.f4)
        f5 = root.findViewById(R.id.f5)
        f6 = root.findViewById(R.id.f6)
        f7 = root.findViewById(R.id.f7)
        f8 = root.findViewById(R.id.f8)
        tvWd = root.findViewById(R.id.wd)
        tvSd = root.findViewById(R.id.sd)
        tvTw = root.findViewById(R.id.tw)
        tvTs = root.findViewById(R.id.ts)
        tvGz = root.findViewById(R.id.gz)
        tvQy = root.findViewById(R.id.qy)
        tvYl = root.findViewById(R.id.yl)
        tvFs = root.findViewById(R.id.fs)
        tvFx = root.findViewById(R.id.fx)
        tvDl = root.findViewById(R.id.dl)
        tvZyl = root.findViewById(R.id.zyl)
        tvFzgl = root.findViewById(R.id.fzgl)
        warmWd = root.findViewById(R.id.warm_wd)
        warmSd = root.findViewById(R.id.warm_sd)
        warmTw = root.findViewById(R.id.warm_tw)
        warmTs = root.findViewById(R.id.warm_ts)
        warmGz = root.findViewById(R.id.warm_gz)
        warmQy = root.findViewById(R.id.warm_qy)
        warmYl = root.findViewById(R.id.warm_yl)
        warmFs = root.findViewById(R.id.warm_fs)
        warmDl = root.findViewById(R.id.warm_dl)
        tvStationId = root.findViewById(R.id.stationId)
        tvName = root.findViewById(R.id.t1)
        val name = UserInfo.name
        tvName!!.text = name
        val pager = root.findViewById<CustomViewPager>(R.id.f_pager)
        val adapter =
            LazyFragmentAdapter(
                childFragmentManager,
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
            )
        fragments.add(F1())
        fragments.add(F2())
        fragments.add(F3())
        fragments.add(F4())
        fragments.add(F5())
        fragments.add(F6())
        fragments.add(F7())
        fragments.add(F8())
        adapter.items = fragments
        pager.adapter = adapter
        f1!!.setOnClickListener {
            bac()
            f1!!.setBackgroundResource(R.drawable.bg_blue)
            f1!!.setTextColor(resources.getColor(R.color.white))
            pager.currentItem = 0
        }
        f2!!.setOnClickListener {
            bac()
            f2!!.setBackgroundResource(R.drawable.bg_blue)
            f2!!.setTextColor(resources.getColor(R.color.white))
            pager.currentItem = 1
        }
        f3!!.setOnClickListener {
            bac()
            f3!!.setBackgroundResource(R.drawable.bg_blue)
            f3!!.setTextColor(resources.getColor(R.color.white))
            pager.currentItem = 2
        }
        f4!!.setOnClickListener {
            bac()
            f4!!.setBackgroundResource(R.drawable.bg_blue)
            f4!!.setTextColor(resources.getColor(R.color.white))
            pager.currentItem = 3
        }
        f5!!.setOnClickListener {
            bac()
            f5!!.setBackgroundResource(R.drawable.bg_blue)
            f5!!.setTextColor(resources.getColor(R.color.white))
            pager.currentItem = 4
        }
        f6!!.setOnClickListener {
            bac()
            f6!!.setBackgroundResource(R.drawable.bg_blue)
            f6!!.setTextColor(resources.getColor(R.color.white))
            pager.currentItem = 5
        }
        f7!!.setOnClickListener {
            bac()
            f7!!.setBackgroundResource(R.drawable.bg_blue)
            f7!!.setTextColor(resources.getColor(R.color.white))
            pager.currentItem = 6
        }
        f8!!.setOnClickListener {
            bac()
            f8!!.setBackgroundResource(R.drawable.bg_blue)
            f8!!.setTextColor(resources.getColor(R.color.white))
            pager.currentItem = 7
        }
        if (UserInfo.live == 0) {
            UserInfo.live = 1
            initView123()
            send1()
        }
    }

    private fun bac() {
        f1!!.setBackgroundResource(R.color.white)
        f2!!.setBackgroundResource(R.color.white)
        f3!!.setBackgroundResource(R.color.white)
        f4!!.setBackgroundResource(R.color.white)
        f5!!.setBackgroundResource(R.color.white)
        f6!!.setBackgroundResource(R.color.white)
        f7!!.setBackgroundResource(R.color.white)
        f8!!.setBackgroundResource(R.color.white)
        f1!!.setTextColor(resources.getColor(R.color.black3))
        f2!!.setTextColor(resources.getColor(R.color.black3))
        f3!!.setTextColor(resources.getColor(R.color.black3))
        f4!!.setTextColor(resources.getColor(R.color.black3))
        f5!!.setTextColor(resources.getColor(R.color.black3))
        f6!!.setTextColor(resources.getColor(R.color.black3))
        f7!!.setTextColor(resources.getColor(R.color.black3))
        f8!!.setTextColor(resources.getColor(R.color.black3))
    }

    var sPool: ScheduledExecutorService? = null
    fun send1() {
        var time = UserInfo.time
        if (time < 5) {
            time = 5
        }
        if (sPool == null) {
            sPool = Executors.newScheduledThreadPool(1)
            sPool!!.scheduleWithFixedDelay(taskOne, 5000, time * 60000L, TimeUnit.MILLISECONDS)
        }else{
            sPool!!.shutdown()
            sPool = null
            sPool = Executors.newScheduledThreadPool(1)
            sPool!!.scheduleWithFixedDelay(taskOne, 5000, time * 60000L, TimeUnit.MILLISECONDS)
        }
    }

    private val taskOne = object : TimerTask() {
        override fun run() {
            //百叶箱
            port1 = 1
            port2 = 0
            port3 = 0
            port4 = 0
            port5 = 0
            port6 = 0
            mPort.sendBytes(StringUtil.toBytes("01030000000C45CF"))
            runDelayedOnUiThread(1500) {
                test()
            }
        }
    }

    fun test() {
        if (port1 == 1) {
            runDelayedOnUiThread(1500) {
                port1 = 0
                port2 = 1
                port3 = 0
                port4 = 0
                port5 = 0
                port6 = 0
                //风速
                mPort.sendBytes(StringUtil.toBytes("0203000000018439"))
                test()
            }
        }
        if (port2 == 1) {
            runDelayedOnUiThread(1500) {
                port1 = 0
                port2 = 0
                port3 = 1
                port4 = 0
                port5 = 0
                port6 = 0
                //风向
                mPort.sendBytes(StringUtil.toBytes("03030000000185E8"))
                test()
            }

        }
        if (port3 == 1) {
            runDelayedOnUiThread(1500) {
                port1 = 0
                port2 = 0
                port3 = 0
                port4 = 1
                port5 = 0
                port6 = 0
                //土壤
                mPort.sendBytes(StringUtil.toBytes("040300000004445C"))
                test()
            }

        }
        if (port4 == 1) {
            runDelayedOnUiThread(1500) {
                port1 = 0
                port2 = 0
                port3 = 0
                port4 = 0
                port5 = 1
                port6 = 0
                //雨量
                mPort.sendBytes(StringUtil.toBytes("0503010900015470"))
                test()
            }
        }
        if (port5 == 1) {
            runDelayedOnUiThread(1500) {
                port1 = 0
                port2 = 0
                port3 = 0
                port4 = 0
                port5 = 0
                port6 = 1
                //电池信息
                mPort.sendBytes(StringUtil.toBytes("060430300028FEAC"))
                test()
            }
        }
        if (port6 == 1) {
            runDelayedOnUiThread(1500) {
                port1 = 0
                port2 = 0
                port3 = 0
                port4 = 0
                port5 = 0
                port6 = 0
                upUiData()
            }
        }
    }
//    //雨量
//    mPort.sendBytes(StringUtil.toBytes("02030109000155c7"))
//    //电池信息
//    mPort.sendBytes(StringUtil.toBytes("030430300028FEF9"))
//    //百叶箱
//    mPort.sendBytes(StringUtil.toBytes("01030000001845c0"))

    //雨量清零
    //mPort.sendBytes(StringUtil.toBytes("020601080001c807"))
    //雨量初始化
    // mPort.sendBytes(StringUtil.toBytes("0206001802268944"))

    var upF1Listenner: UpF1Listenner? = null

    interface UpF1Listenner {
        fun send(str: String)
    }

    fun setF1Listenner(upF1Listenner: UpF1Listenner?) {
        this.upF1Listenner = upF1Listenner
    }

    private fun setBj(tv: TextView, dr: Drawable) {
        tv.setCompoundDrawablesWithIntrinsicBounds(dr, null, null, null)
    }

    @SuppressLint("SimpleDateFormat")
    val sdr = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    @SuppressLint("SetTextI18n")
    private fun upUiData() {
        //存数据库
        val times1 = sdr.format(Date().time)
        val weather = Weather()
        weather.time = times1
        weather.wd = "$wendu"
        weather.sd = "$shidu"
        weather.tw = "$twendu"
        weather.ts = "$tshidu"
        weather.gz = "$gzhao"
        weather.qy = qyz
        weather.yl = "$yulMin"
        weather.zyl = "$yul"
        weather.fs = "$fsu"
        weather.fx = "$fxiang"
        weather.save()
        val weathers: List<Weather> = SQLite.select()
            .from(Weather::class.java)
            .where()
            .orderBy(Weather_Table.id, false)
            .limit(501)//限制条数
            .queryList()
        if (weathers.isEmpty()) {
            return
        }
        if (weathers.size > 500) {
            Delete.table(Weather::class.java)
        }
        val dc: List<Dc> = SQLite.select()
            .from(Dc::class.java)
            .where()
            .limit(501)//限制条数
            .orderBy(Dc_Table.id, false)//按照升序
            .queryList()
        if (dc.size > 500) {
            Delete.table(Dc::class.java)
        }
        dataList.clear()
        for (y in 0..11) {
            val metadata = UpData.MetaBean()
            when (y) {
                0 -> {
                    metadata.acqData = "" + weathers[0].wd
                    metadata.acqItem = "WD"
                }
                1 -> {
                    metadata.acqData = "" + weathers[0].sd
                    metadata.acqItem = "SD"
                }
                2 -> {
                    metadata.acqData = "" + weathers[0].tw
                    metadata.acqItem = "TRWD"
                }
                3 -> {
                    metadata.acqData = "" + weathers[0].ts
                    metadata.acqItem = "TRSD"
                }
                4 -> {
                    metadata.acqData = "" + weathers[0].gz
                    metadata.acqItem = "GZQD"
                }
                5 -> {
                    metadata.acqData = "" + weathers[0].qy
                    metadata.acqItem = "DQYL"
                }
                6 -> {
                    metadata.acqData = "" + weathers[0].yl
                    metadata.acqItem = "JYL"
                }
                7 -> {
                    metadata.acqItem = "FX"
                    metadata.acqData = weathers[0].fx
                }
                8 -> {
                    metadata.acqData = "" + weathers[0].fs
                    metadata.acqItem = "FS"
                }
                9 -> {
                    if (dc.isNotEmpty()) {
                        metadata.acqData = "" + dc[0].xdNum
                    } else {
                        metadata.acqData = "50"
                    }
                    metadata.acqItem = "SYDL"
                }
                10 -> {
                    if (dc.isNotEmpty()) {
                        metadata.acqData = "" + dc[0].fzgl

                    } else {
                        metadata.acqData = "3"
                    }
                    metadata.acqItem = "FZGL"
                }
                11 -> {
                    metadata.acqData = "" + weathers[0].zyl
                    metadata.acqItem = "JYLDAY"
                }
            }
            dataList.add(metadata)
        }
        val upData = UpData()
        upData.dataTime = weathers[0].time
        upData.stationCode = UserInfo.id
        upData.weatherItems = dataList
        upData.type = "1"
        upF1Listenner!!.send(JSON.toJSONString(upData))

        //更新图表
        if (fragments.size == 8) {
            val tb1 = fragments[0] as F1
            val tb2 = fragments[1] as F2
            val tb3 = fragments[2] as F3
            val tb4 = fragments[3] as F4
            val tb5 = fragments[4] as F5
            val tb6 = fragments[5] as F6
            val tb7 = fragments[6] as F7
            val tb8 = fragments[7] as F8
            if (tb1.isShow()) {
                tb1.setData()
            }
            if (tb2.isShow()) {
                tb2.setData()
            }
            if (tb3.isShow()) {
                tb3.setData()
            }
            if (tb4.isShow()) {
                tb4.setData()
            }
            if (tb5.isShow()) {
                tb5.setData()
            }
            if (tb6.isShow()) {
                tb6.setData()
            }
            if (tb7.isShow()) {
                tb7.setData()
            }
            if (tb8.isShow()) {
                tb8.setData()
            }
        }
        //报警设置
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
        val bj = context?.let {
            ContextCompat.getDrawable(
                it,
                R.mipmap.img_bj3
            )
        }
        val zc = context?.let {
            ContextCompat.getDrawable(
                it,
                R.mipmap.img_bj1
            )
        }

        //更新界面数据
            tvTime!!.text = weathers[0].time
            tvWd!!.text = "${weathers[0].wd}℃"
            tvSd!!.text = "${weathers[0].sd}%"
            tvTw!!.text = "${weathers[0].tw}℃"
            tvTs!!.text = "${weathers[0].ts}%"
            tvGz!!.text = "${weathers[0].gz} Lux"
            tvQy!!.text = weathers[0].qy + "Hpa"
            tvFs!!.text = "${weathers[0].fs} m/s"
            tvYl!!.text = "${weathers[0].yl} mm/min"
            tvZyl!!.text = "${weathers[0].zyl} mm"
        LogUtils.i(tvWd!!.text.toString())
            when (weathers[0].fx.toInt()) {
                0 -> {
                    tvFx!!.text = "风向：北风"
                }
                90 -> {
                    tvFx!!.text = "风向：东风"
                }
                180 -> {
                    tvFx!!.text = "风向：南风"
                }
                270 -> {
                    tvFx!!.text = "风向：西风"
                }
                360 -> {
                    tvFx!!.text = "风向：北风"
                }
                in 0..90 -> {
                    tvFx!!.text = "风向：东北风"
                }
                in 90..180 -> {
                    tvFx!!.text = "风向：东南风"
                }
                in 180..270 -> {
                    tvFx!!.text = "风向：西南风"
                }
                in 270..359 -> {
                    tvFx!!.text = "风向：西北风"
                }
            }
                if (!TextUtils.isEmpty(kwUp)) {
                    when {
                        kwUp!!.toDouble() < wendu -> {
                            setBj(warmWd!!, bj!!)
                            warmWd!!.text = "空温超出上限"
                        }
                        kwDown!!.toDouble() > wendu -> {
                            setBj(warmWd!!, bj!!)
                            warmWd!!.text = "空温低于下限"
                        }
                        else -> {
                            setBj(warmWd!!, zc!!)
                            warmWd!!.text = "空温数值正常"
                        }
                    }
                } else {
                    warmWd!!.text = "空温暂未设置报警"
                    setBj(warmWd!!, bj!!)
                }

                if (!TextUtils.isEmpty(ksUp)) {
                    when {
                        ksUp!!.toDouble() < shidu -> {
                            setBj(warmSd!!, bj!!)
                            warmSd!!.text = "空湿超出上限"
                        }
                        ksDown!!.toDouble() > shidu -> {
                            setBj(warmWd!!, bj!!)
                            warmSd!!.text = "空湿低于下限"
                        }
                        else -> {
                            setBj(warmWd!!, zc!!)
                            warmSd!!.text = "空湿数值正常"
                        }
                    }
                } else {
                    warmSd!!.text = "空湿暂未设置报警"
                    setBj(warmSd!!, bj!!)
                }

                if (!TextUtils.isEmpty(twUp)) {
                    when {
                        twUp!!.toDouble() < twendu -> {
                            setBj(warmTw!!, bj!!)
                            warmTw!!.text = "土温超出上限"
                        }
                        twDown!!.toDouble() > twendu -> {
                            setBj(warmTw!!, bj!!)
                            warmTw!!.text = "土温低于下限"
                        }
                        else -> {
                            setBj(warmTw!!, zc!!)
                            warmTw!!.text = "土温数值正常"
                        }
                    }
                } else {
                    warmTw!!.text = "土温暂未设置报警"
                    setBj(warmTw!!, bj!!)
                }

                if (!TextUtils.isEmpty(tsUp)) {
                    when {
                        tsUp!!.toDouble() < tshidu -> {
                            setBj(warmTs!!, bj!!)
                            warmTs!!.text = "土湿超出上限"
                        }
                        tsDown!!.toDouble() > tshidu -> {
                            setBj(warmTs!!, bj!!)
                            warmTs!!.text = "土湿低于下限"
                        }
                        else -> {
                            setBj(warmTs!!, zc!!)
                            warmTs!!.text = "土湿数值正常"
                        }
                    }
                } else {
                    warmTs!!.text = "土湿暂未设置报警"
                    setBj(warmTs!!, bj!!)
                }

                if (!TextUtils.isEmpty(gzUp)) {
                    when {
                        gzUp!!.toDouble() < gzhao -> {
                            setBj(warmGz!!, bj!!)
                            warmGz!!.text = "光照超出上限"
                        }
                        gzDown!!.toDouble() > gzhao -> {
                            setBj(warmGz!!, bj!!)
                            warmGz!!.text = "光照低于下限"
                        }
                        else -> {
                            setBj(warmGz!!, zc!!)
                            warmGz!!.text = "光照数值正常"
                        }
                    }
                } else {
                    warmGz!!.text = "光照暂未设置报警"
                    setBj(warmGz!!, bj!!)
                }

                if (!TextUtils.isEmpty(qyUp)) {
                    when {
                        qyUp!!.toDouble() < qyz.toDouble() -> {
                            setBj(warmQy!!, bj!!)
                            warmQy!!.text = "气压超出上限"
                        }
                        qyDown!!.toDouble() > qyz.toDouble() -> {
                            setBj(warmQy!!, bj!!)
                            warmQy!!.text = "气压低于下限"
                        }
                        else -> {
                            setBj(warmQy!!, zc!!)
                            warmQy!!.text = "气压数值正常"
                        }
                    }
                } else {
                    warmQy!!.text = "气压暂未设置报警"
                    setBj(warmQy!!, bj!!)
                }

                if (!TextUtils.isEmpty(ylUp)) {
                    when {
                        ylUp!!.toDouble() < yul -> {
                            setBj(warmYl!!, bj!!)
                            warmYl!!.text = "雨量超出上限"
                        }
                        ylDown!!.toDouble() > yul -> {
                            setBj(warmYl!!, bj!!)
                            warmYl!!.text = "雨量低于下限"
                        }
                        else -> {
                            setBj(warmYl!!, zc!!)
                            warmYl!!.text = "雨量数值正常"
                        }
                    }
                } else {
                    warmYl!!.text = "雨量暂未设置报警"
                    setBj(warmYl!!, bj!!)
                }

                if (!TextUtils.isEmpty(fsUp)) {
                    when {
                        fsUp!!.toDouble() < fsu -> {
                            setBj(warmFs!!, bj!!)
                            warmFs!!.text = "风速超出上限"
                        }
                        fsDown!!.toDouble() > fsu -> {
                            setBj(warmFs!!, bj!!)
                            warmFs!!.text = "风速低于下限"
                        }
                        else -> {
                            setBj(warmFs!!, zc!!)
                            warmFs!!.text = "风速数值正常"
                        }
                    }
                } else {
                    warmFs!!.text = "风速暂未设置报警"
                    setBj(warmFs!!, bj!!)
                }
            utils!!.showToast("已保存")


        if (dc.isNotEmpty()) {
            //电量预警
            val fzgl = dc[0].fzgl
            val xuNum = dc[0].xdNum.toInt()
            tvFzgl!!.text = "$fzgl W"
            tvDl!!.text = "$xuNum%"
            val dlDowm = UserInfo.dlDown

                if (!TextUtils.isEmpty(dlDowm)) {
                    if (xuNum < dlDowm!!.toInt()) {
                        warmDl!!.setCompoundDrawablesWithIntrinsicBounds(
                            bj,
                            null, null, null
                        )
                        warmDl!!.text = "电量低于下限"
                    } else {
                        warmDl!!.setCompoundDrawablesWithIntrinsicBounds(
                            zc,
                            null, null, null
                        )
                        warmDl!!.text = "电量数值正常"
                    }
                }

        }
    }

    var mPort = SerialPortManager()
    var yulMin = 0.0
    var yul = 0.0
    var dataList = arrayListOf<UpData.MetaBean>()
    var tians = 0
    var xuzt = ""
    var chzt = ""
    var fazt = ""
    var huanwd = 0.0
    var guof = 0
    var chm = 0
    var guoy = 0
    var guol = 0
    var duanl = 0
    var kail = 0
    var yj = ""
    var port1 = 0
    var port2 = 0
    var port3 = 0
    var port4 = 0
    var port5 = 0
    var port6 = 0
    var wendu = 0.0
    var shidu = 0.0
    var fsu = 0.0
    var fxiang = 0
    var twendu = 0.0
    var tshidu = 0.0
    var gzhao = 0.0
    var qyz = "0"
    private fun initView123() {
        //初始化串口
        val serialPortFinder = SerialPortFinder()
        val devices: java.util.ArrayList<Device> = serialPortFinder.devices
        for (i in devices.indices) {
            val file: File = devices[i].file
            val canRead = file.canRead()
            val canWrite = file.canWrite()
            if (devices[i].name == "ttyS3") {
                if (canRead && canWrite) {
                    val device: Device = devices[i]
                    mPort.openSerialPort(device.file, 9600)
                    mPort.startSendThread()
                    mPort.startReadThread()
                    mPort.setOnSerialPortDataListener(object : OnSerialPortDataListener {
                        override fun onDataSent(bytes: ByteArray) {
                            // runOnUiThread { utils.showToast("串口发送: " + bytes.contentToString()) }

                        }

                        @SuppressLint("SetTextI18n", "SimpleDateFormat")
                        override fun onDataReceived(bytes: ByteArray?) {
                            // runOnUiThread { utils.showToast("串口接受: " + bytes.contentToString()) }
                            if (bytes!!.size == 29 && port1 == 1) {
                                val sd = ByteUtil.byteArrToHexString(byteArrayOf(bytes[3]))
                                val sd1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[4]))
                                val wd = ByteUtil.byteArrToHexString(byteArrayOf(bytes[5]))
                                val wd1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[6]))
                                val gz = ByteUtil.byteArrToHexString(byteArrayOf(bytes[17]))
                                val gz1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[18]))
                                val gz2 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[19]))
                                val gz3 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[20]))
                                val qy = ByteUtil.byteArrToHexString(byteArrayOf(bytes[23]))
                                val qy1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[24]))
                                val qy2 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[25]))
                                val qy3 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[26]))
                                wendu = if (Integer.valueOf(wd + wd1, 16) > 800) {
                                    (bytes[5] + bytes[6]).toDouble() / 10
                                } else {
                                    Integer.valueOf(wd + wd1, 16).toDouble() / 10
                                }
                                shidu = Integer.valueOf(sd + sd1, 16).toDouble() / 10

                                val gzhao0 = Integer.valueOf(gz + gz1, 16).toDouble()
                                val gzhao1 = Integer.valueOf(gz2 + gz3, 16).toDouble()
                                gzhao = gzhao1 + gzhao0

                                val qya0 = Integer.valueOf(qy + qy1, 16).toDouble() / 10
                                val qya1 = Integer.valueOf(qy2 + qy3, 16).toDouble() / 10
                                qyz = utils!!.save2(qya1 + qya0)
                            }
                            if (bytes.size == 7 && port2 == 1) {
                                val fs = ByteUtil.byteArrToHexString(byteArrayOf(bytes[3]))
                                val fs1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[4]))
                                fsu = Integer.valueOf(fs + fs1, 16).toDouble() / 10
                            }
                            if (bytes.size == 7 && port3 == 1) {
                                val fx = ByteUtil.byteArrToHexString(byteArrayOf(bytes[3]))
                                val fx1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[4]))
                                fxiang = Integer.valueOf(fx + fx1, 16)
                            }
                            if (bytes.size == 13 && port4 == 1) {
                                val trsd = ByteUtil.byteArrToHexString(byteArrayOf(bytes[7]))
                                val trsd1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[8]))
                                val trwd = ByteUtil.byteArrToHexString(byteArrayOf(bytes[9]))
                                val trwd1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[10]))
                                twendu = if (Integer.valueOf(trwd + trwd1, 16) > 800) {
                                    (bytes[9] + bytes[10]).toDouble() / 10
                                } else {
                                    Integer.valueOf(trwd + trwd1, 16).toDouble() / 10
                                }
                                tshidu = Integer.valueOf(trsd + trsd1, 16).toDouble() / 10

                            }
                            if (bytes.size == 7 && port5 == 1) {
                                val yl = ByteUtil.byteArrToHexString(byteArrayOf(bytes[3]))
                                val yl1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[4]))
                                yul = Integer.valueOf(yl + yl1, 16).toDouble() / 10
                                val decodeInt = UserInfo.time
                                if (yul <= decodeInt) {
                                    yulMin = yul
                                } else {
                                    yulMin =
                                        utils.floatSave2((yul / decodeInt).toFloat()).toDouble()
                                }
                            }
                            if (bytes.size == 85 && port6 == 1) {
                                val ts = ByteUtil.byteArrToHexString(byteArrayOf(bytes[5]))
                                val ts1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[6]))
                                tians = Integer.valueOf(ts + ts1, 16)
                                //val xzt = ByteUtil.byteArrToHexString(byteArrayOf(bytes[9]))
                                val xzt1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[10]))
                                //val czt = ByteUtil.byteArrToHexString(byteArrayOf(bytes[11]))
                                val czt1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[12]))
                                //val fzt = ByteUtil.byteArrToHexString(byteArrayOf(bytes[13]))
                                val fzt1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[14]))
                                val hwd = ByteUtil.byteArrToHexString(byteArrayOf(bytes[15]))
                                val hwd1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[16]))
                                val gf = ByteUtil.byteArrToHexString(byteArrayOf(bytes[19]))
                                val gf1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[20]))
                                val cm = ByteUtil.byteArrToHexString(byteArrayOf(bytes[21]))
                                val cm1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[22]))
                                val gy = ByteUtil.byteArrToHexString(byteArrayOf(bytes[23]))
                                val gy1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[24]))
                                val gl = ByteUtil.byteArrToHexString(byteArrayOf(bytes[25]))
                                val gl1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[26]))
                                val dul = ByteUtil.byteArrToHexString(byteArrayOf(bytes[27]))
                                val dul1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[28]))
                                val kl = ByteUtil.byteArrToHexString(byteArrayOf(bytes[29]))
                                val kl1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[30]))
                                yj = ByteUtil.byteArrToHexString(byteArrayOf(bytes[31]))
                                val yj1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[32]))
                                val cdgw = ByteUtil.byteArrToHexString(byteArrayOf(bytes[33]))
                                val cdgw1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[34]))
                                val fdgw = ByteUtil.byteArrToHexString(byteArrayOf(bytes[35]))
                                val fdgw1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[36]))
                                val xnum = ByteUtil.byteArrToHexString(byteArrayOf(bytes[45]))
                                val xnum1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[46]))
                                val xdy = ByteUtil.byteArrToHexString(byteArrayOf(bytes[47]))
                                val xdy1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[48]))
                                val xdl = ByteUtil.byteArrToHexString(byteArrayOf(bytes[49]))
                                val xdl1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[50]))
                                val xdgl_l = ByteUtil.byteArrToHexString(byteArrayOf(bytes[51]))
                                val xdgl_l1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[52]))
                                val xdgl_h = ByteUtil.byteArrToHexString(byteArrayOf(bytes[53]))
                                val xdgl_h1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[54]))
                                val fdy = ByteUtil.byteArrToHexString(byteArrayOf(bytes[55]))
                                val fdy1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[56]))
                                val fdl = ByteUtil.byteArrToHexString(byteArrayOf(bytes[57]))
                                val fdl1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[58]))
                                val fz_l = ByteUtil.byteArrToHexString(byteArrayOf(bytes[59]))
                                val fz_l1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[60]))
                                val fz_h = ByteUtil.byteArrToHexString(byteArrayOf(bytes[61]))
                                val fz_h1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[62]))
                                val tdy = ByteUtil.byteArrToHexString(byteArrayOf(bytes[63]))
                                val tdy1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[64]))
                                val tdl = ByteUtil.byteArrToHexString(byteArrayOf(bytes[65]))
                                val tdl1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[66]))
                                val fgl_l = ByteUtil.byteArrToHexString(byteArrayOf(bytes[67]))
                                val fgl_l1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[68]))
                                val fgl_h = ByteUtil.byteArrToHexString(byteArrayOf(bytes[69]))
                                val fgl_h1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[70]))
                                val dayc = ByteUtil.byteArrToHexString(byteArrayOf(bytes[71]))
                                val dayc1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[72]))
                                val allc_l = ByteUtil.byteArrToHexString(byteArrayOf(bytes[73]))
                                val allc_l1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[74]))
                                val allc_h = ByteUtil.byteArrToHexString(byteArrayOf(bytes[75]))
                                val allc_h1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[76]))
                                val day_y = ByteUtil.byteArrToHexString(byteArrayOf(bytes[77]))
                                val day_y1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[78]))
                                val ally_l = ByteUtil.byteArrToHexString(byteArrayOf(bytes[79]))
                                val ally_l1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[80]))
                                val ally_h = ByteUtil.byteArrToHexString(byteArrayOf(bytes[81]))
                                val ally_h1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[82]))
                                //运行天数
                                tians = Integer.valueOf(ts + ts1, 16)
                                //蓄电池状态
                                when (xzt1) {
                                    "00" -> xuzt = "正常"
                                    "01" -> xuzt = "超压"
                                    "02" -> xuzt = "欠压"
                                    "03" -> xuzt = "过放"
                                }

                                //充电设备状态
                                when (czt1) {
                                    "09" -> chzt = "充电中"
                                    else -> chzt = "未充电"
                                }

                                //放电设备状态
                                when (fzt1) {
                                    "00" -> fazt = "轻载"
                                    "01" -> fazt = "中度"
                                    "02" -> fazt = "额定"
                                    "03" -> fazt = "过载"
                                }
                                //环境温度
                                huanwd = Integer.valueOf(hwd + hwd1, 16).toDouble() / 100
                                //过放次数
                                guof = Integer.valueOf(gf + gf1, 16)
                                //充满次数
                                chm = Integer.valueOf(cm + cm1, 16)
                                //过压保护次数
                                guoy = Integer.valueOf(gy + gy1, 16)
                                //过流保护次数
                                guol = Integer.valueOf(gl + gl1, 16)
                                //短路保护次数
                                duanl = Integer.valueOf(dul + dul1, 16)
                                //开路保护次数
                                kail = Integer.valueOf(kl + kl1, 16)
                                //硬件保护次数
                                val yingj = Integer.valueOf(yj + yj1, 16)
                                //充电过温保护次数
                                val chgw = Integer.valueOf(cdgw + cdgw1, 16)
                                //放电过温保护次数
                                val fagw = Integer.valueOf(fdgw + fdgw1, 16)
                                //蓄电池电量
                                val xuNum = Integer.valueOf(xnum + xnum1, 16)
                                //蓄电池电压
                                val xudy = Integer.valueOf(xdy + xdy1, 16).toDouble() / 100
                                //蓄电池电流
                                val xudl = if (Integer.valueOf(xdl + xdl1, 16) > 600) {
                                    (bytes[17] + bytes[18]).toDouble() / 100
                                } else {
                                    Integer.valueOf(xdl + xdl1, 16).toDouble() / 100
                                }
                                //蓄电池功率
                                val x = if (Integer.valueOf(xdgl_l + xdgl_l1, 16) > 600) {
                                    (bytes[19] + bytes[20]).toDouble() / 100
                                } else {
                                    Integer.valueOf(xdgl_l + xdgl_l1, 16).toDouble() / 100
                                }
                                val x1 = if (Integer.valueOf(xdgl_h + xdgl_h1, 16) > 600) {
                                    (bytes[21] + bytes[22]).toDouble() / 100
                                } else {
                                    Integer.valueOf(xdgl_h + xdgl_h1, 16).toDouble() / 100
                                }
                                val xdcgl = x1 * 65536 + x
                                //负载电压
                                val fudy = Integer.valueOf(fdy + fdy1, 16).toDouble() / 100
                                //负载电流
                                val fudl = Integer.valueOf(fdl + fdl1, 16).toDouble() / 100
                                //负载功率
                                val f = Integer.valueOf(fz_l + fz_l1, 16) / 100
                                val f1 = Integer.valueOf(fz_h + fz_h1, 16) / 100
                                val fzgl = f1 * 65536 + f
                                //太阳能电压
                                val taidy = Integer.valueOf(tdy + tdy1, 16).toDouble() / 100
                                //太阳能电流
                                val taidl = Integer.valueOf(tdl + tdl1, 16).toDouble() / 100
                                //发电功率
                                val fd = Integer.valueOf(fgl_l + fgl_l1, 16) / 100
                                val fd1 = Integer.valueOf(fgl_h + fgl_h1, 16) / 100
                                val fdgl = fd1 * 65536 + fd
                                //当日累计充电量
                                val daycd = Integer.valueOf(dayc + dayc1, 16).toDouble() / 100
                                //总累计充电量
                                val allcd_l = Integer.valueOf(allc_l + allc_l1, 16) / 100
                                val allcd_h = Integer.valueOf(allc_h + allc_h1, 16) / 100
                                val allCd = allcd_h * 65536 + allcd_l
                                //当日累计用电量
                                val dayyd = Integer.valueOf(day_y + day_y1, 16).toDouble() / 100
                                //总累计用电量
                                val allyd_l = Integer.valueOf(ally_l + ally_l1, 16) / 100
                                val allyd_h = Integer.valueOf(ally_h + ally_h1, 16) / 100
                                val allYd = allyd_h * 65536 + allyd_l
                                //H * 65536 + L
                                val dc = Dc()
                                dc.ts = "$tians"
                                dc.xzt = xuzt
                                dc.czt = chzt
                                dc.fzt = fazt
                                dc.hwd = "$huanwd"
                                dc.gf = "$guof"
                                dc.cm = "$chm"
                                dc.gy = "$guoy"
                                dc.gl = "$guol"
                                dc.dl = "$duanl"
                                dc.kl = "$kail"
                                dc.yj = "$yingj"
                                dc.cgw = "$chgw"
                                dc.fgw = "$fagw"
                                dc.xdNum = "$xuNum"
                                dc.xdy = "$xudy"
                                dc.xdl = "$xudl"
                                dc.xgl = "$xdcgl"
                                dc.fdy = "$fudy"
                                dc.fdl = "$fudl"
                                dc.fzgl = "$fzgl"
                                dc.tdy = "$taidy"
                                dc.tdl = "$taidl"
                                dc.fdgl = "$fdgl"
                                dc.dayc = "$daycd"
                                dc.dayy = "$dayyd"
                                dc.allc = "$allCd"
                                dc.ally = "$allYd"
                                dc.save()
                            }
                            if (bytes.size == 32 && port6 == 1) {
                                val ts = ByteUtil.byteArrToHexString(byteArrayOf(bytes[5]))
                                val ts1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[6]))
                                tians = Integer.valueOf(ts + ts1, 16)
                                //val xzt = ByteUtil.byteArrToHexString(byteArrayOf(bytes[9]))
                                val xzt1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[10]))
                                //val czt = ByteUtil.byteArrToHexString(byteArrayOf(bytes[11]))
                                val czt1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[12]))
                                //val fzt = ByteUtil.byteArrToHexString(byteArrayOf(bytes[13]))
                                val fzt1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[14]))
                                val hwd = ByteUtil.byteArrToHexString(byteArrayOf(bytes[15]))
                                val hwd1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[16]))
                                val gf = ByteUtil.byteArrToHexString(byteArrayOf(bytes[19]))
                                val gf1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[20]))
                                val cm = ByteUtil.byteArrToHexString(byteArrayOf(bytes[21]))
                                val cm1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[22]))
                                val gy = ByteUtil.byteArrToHexString(byteArrayOf(bytes[23]))
                                val gy1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[24]))
                                val gl = ByteUtil.byteArrToHexString(byteArrayOf(bytes[25]))
                                val gl1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[26]))
                                val dul = ByteUtil.byteArrToHexString(byteArrayOf(bytes[27]))
                                val dul1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[28]))
                                val kl = ByteUtil.byteArrToHexString(byteArrayOf(bytes[29]))
                                val kl1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[30]))
                                yj = ByteUtil.byteArrToHexString(byteArrayOf(bytes[31]))
                                //运行天数
                                tians = Integer.valueOf(ts + ts1, 16)
                                //蓄电池状态
                                when (xzt1) {
                                    "00" -> xuzt = "正常"
                                    "01" -> xuzt = "超压"
                                    "02" -> xuzt = "欠压"
                                    "03" -> xuzt = "过放"
                                }

                                //充电设备状态
                                when (czt1) {
                                    "09" -> chzt = "充电中"
                                    else -> chzt = "未充电"
                                }

                                //放电设备状态
                                when (fzt1) {
                                    "00" -> fazt = "轻载"
                                    "01" -> fazt = "中度"
                                    "02" -> fazt = "额定"
                                    "03" -> fazt = "过载"
                                }
                                //环境温度
                                huanwd = Integer.valueOf(hwd + hwd1, 16).toDouble() / 100
                                //过放次数
                                guof = Integer.valueOf(gf + gf1, 16)
                                //充满次数
                                chm = Integer.valueOf(cm + cm1, 16)
                                //过压保护次数
                                guoy = Integer.valueOf(gy + gy1, 16)
                                //过流保护次数
                                guol = Integer.valueOf(gl + gl1, 16)
                                //短路保护次数
                                duanl = Integer.valueOf(dul + dul1, 16)
                                //开路保护次数
                                kail = Integer.valueOf(kl + kl1, 16)
                            }
                            if (bytes.size == 53 && port6 == 1) {
                                val yj1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[0]))
                                val cdgw = ByteUtil.byteArrToHexString(byteArrayOf(bytes[1]))
                                val cdgw1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[2]))
                                val fdgw = ByteUtil.byteArrToHexString(byteArrayOf(bytes[3]))
                                val fdgw1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[4]))
                                val xnum = ByteUtil.byteArrToHexString(byteArrayOf(bytes[13]))
                                val xnum1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[14]))
                                val xdy = ByteUtil.byteArrToHexString(byteArrayOf(bytes[15]))
                                val xdy1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[16]))
                                val xdl = ByteUtil.byteArrToHexString(byteArrayOf(bytes[17]))
                                val xdl1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[18]))
                                val xdgl_l = ByteUtil.byteArrToHexString(byteArrayOf(bytes[19]))
                                val xdgl_l1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[20]))
                                val xdgl_h = ByteUtil.byteArrToHexString(byteArrayOf(bytes[21]))
                                val xdgl_h1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[22]))
                                val fdy = ByteUtil.byteArrToHexString(byteArrayOf(bytes[23]))
                                val fdy1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[24]))
                                val fdl = ByteUtil.byteArrToHexString(byteArrayOf(bytes[25]))
                                val fdl1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[26]))
                                val fz_l = ByteUtil.byteArrToHexString(byteArrayOf(bytes[27]))
                                val fz_l1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[28]))
                                val fz_h = ByteUtil.byteArrToHexString(byteArrayOf(bytes[29]))
                                val fz_h1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[30]))
                                val tdy = ByteUtil.byteArrToHexString(byteArrayOf(bytes[31]))
                                val tdy1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[32]))
                                val tdl = ByteUtil.byteArrToHexString(byteArrayOf(bytes[33]))
                                val tdl1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[34]))
                                val fgl_l = ByteUtil.byteArrToHexString(byteArrayOf(bytes[35]))
                                val fgl_l1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[36]))
                                val fgl_h = ByteUtil.byteArrToHexString(byteArrayOf(bytes[37]))
                                val fgl_h1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[38]))
                                val dayc = ByteUtil.byteArrToHexString(byteArrayOf(bytes[39]))
                                val dayc1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[40]))
                                val allc_l = ByteUtil.byteArrToHexString(byteArrayOf(bytes[41]))
                                val allc_l1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[42]))
                                val allc_h = ByteUtil.byteArrToHexString(byteArrayOf(bytes[43]))
                                val allc_h1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[44]))
                                val day_y = ByteUtil.byteArrToHexString(byteArrayOf(bytes[45]))
                                val day_y1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[46]))
                                val ally_l = ByteUtil.byteArrToHexString(byteArrayOf(bytes[47]))
                                val ally_l1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[48]))
                                val ally_h = ByteUtil.byteArrToHexString(byteArrayOf(bytes[49]))
                                val ally_h1 = ByteUtil.byteArrToHexString(byteArrayOf(bytes[50]))
                                //硬件保护次数
                                val yingj = Integer.valueOf(yj + yj1, 16)
                                //充电过温保护次数
                                val chgw = Integer.valueOf(cdgw + cdgw1, 16)
                                //放电过温保护次数
                                val fagw = Integer.valueOf(fdgw + fdgw1, 16)
                                //蓄电池电量
                                val xuNum = Integer.valueOf(xnum + xnum1, 16)
                                //蓄电池电压
                                val xudy = Integer.valueOf(xdy + xdy1, 16).toDouble() / 100
                                //蓄电池电流
                                val xudl = if (Integer.valueOf(xdl + xdl1, 16) > 600) {
                                    (bytes[17] + bytes[18]).toDouble() / 100
                                } else {
                                    Integer.valueOf(xdl + xdl1, 16).toDouble() / 100
                                }
                                //蓄电池功率
                                val x = if (Integer.valueOf(xdgl_l + xdgl_l1, 16) > 600) {
                                    (bytes[19] + bytes[20]).toDouble() / 100
                                } else {
                                    Integer.valueOf(xdgl_l + xdgl_l1, 16).toDouble() / 100
                                }
                                val x1 = if (Integer.valueOf(xdgl_h + xdgl_h1, 16) > 600) {
                                    (bytes[21] + bytes[22]).toDouble() / 100
                                } else {
                                    Integer.valueOf(xdgl_h + xdgl_h1, 16).toDouble() / 100
                                }
                                val xdcgl = x1 * 65536 + x
                                //负载电压
                                val fudy = Integer.valueOf(fdy + fdy1, 16).toDouble() / 100
                                //负载电流
                                val fudl = Integer.valueOf(fdl + fdl1, 16).toDouble() / 100
                                //负载功率
                                val f = Integer.valueOf(fz_l + fz_l1, 16) / 100
                                val f1 = Integer.valueOf(fz_h + fz_h1, 16) / 100
                                val fzgl = f1 * 65536 + f
                                //太阳能电压
                                val taidy = Integer.valueOf(tdy + tdy1, 16).toDouble() / 100
                                //太阳能电流
                                val taidl = Integer.valueOf(tdl + tdl1, 16).toDouble() / 100
                                //发电功率
                                val fd = Integer.valueOf(fgl_l + fgl_l1, 16) / 100
                                val fd1 = Integer.valueOf(fgl_h + fgl_h1, 16) / 100
                                val fdgl = fd1 * 65536 + fd
                                //当日累计充电量
                                val daycd = Integer.valueOf(dayc + dayc1, 16).toDouble() / 100
                                //总累计充电量
                                val allcd_l = Integer.valueOf(allc_l + allc_l1, 16) / 100
                                val allcd_h = Integer.valueOf(allc_h + allc_h1, 16) / 100
                                val allCd = allcd_h * 65536 + allcd_l
                                //当日累计用电量
                                val dayyd = Integer.valueOf(day_y + day_y1, 16).toDouble() / 100
                                //总累计用电量
                                val allyd_l = Integer.valueOf(ally_l + ally_l1, 16) / 100
                                val allyd_h = Integer.valueOf(ally_h + ally_h1, 16) / 100
                                val allYd = allyd_h * 65536 + allyd_l
                                //H * 65536 + L
                                val dc = Dc()
                                dc.ts = "$tians"
                                dc.xzt = xuzt
                                dc.czt = chzt
                                dc.fzt = fazt
                                dc.hwd = "$huanwd"
                                dc.gf = "$guof"
                                dc.cm = "$chm"
                                dc.gy = "$guoy"
                                dc.gl = "$guol"
                                dc.dl = "$duanl"
                                dc.kl = "$kail"
                                dc.yj = "$yingj"
                                dc.cgw = "$chgw"
                                dc.fgw = "$fagw"
                                dc.xdNum = "$xuNum"
                                dc.xdy = "$xudy"
                                dc.xdl = "$xudl"
                                dc.xgl = "$xdcgl"
                                dc.fdy = "$fudy"
                                dc.fdl = "$fudl"
                                dc.fzgl = "$fzgl"
                                dc.tdy = "$taidy"
                                dc.tdl = "$taidl"
                                dc.fdgl = "$fdgl"
                                dc.dayc = "$daycd"
                                dc.dayy = "$dayyd"
                                dc.allc = "$allCd"
                                dc.ally = "$allYd"
                                dc.save()
                            }
                        }
                    })
                    break
                }
            }
        }

    }

    fun isShow(): Boolean {
        return this@Fragment1.isResumed && this@Fragment1.userVisibleHint
    }
}