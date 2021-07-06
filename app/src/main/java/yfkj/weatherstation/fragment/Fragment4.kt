package yfkj.weatherstation.fragment

import android.text.InputFilter
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.TextView
import yfkj.weatherstation.R
import yfkj.weatherstation.activity.Main
import yfkj.weatherstation.utils.InFilter
import yfkj.weatherstation.utils.MoneyInFilter
import yfkj.weatherstation.utils.UserInfo

class Fragment4 : LazyFragment() {
    var wdUp: EditText? = null
    var wdDown: EditText? = null
    var sdUp: EditText? = null
    var sdDown: EditText? = null
    var twdUp: EditText? = null
    var twdDown: EditText? = null
    var tsdUp: EditText? = null
    var tsdDown: EditText? = null
    var gzUp1: EditText? = null
    var gzDown1: EditText? = null
    var qyUp1: EditText? = null
    var qyDown1: EditText? = null
    var ylUp1: EditText? = null
    var ylDown1: EditText? = null
    var fsUp1: EditText? = null
    var fsDown1: EditText? = null
    var etTime: EditText? = null
    var dlDown: EditText? = null
    var save: TextView? = null

    var upF4Listenner: UpF4Listenner? = null

    interface UpF4Listenner {
        fun send4()
    }

    fun setF4Listenner(upF4Listenner: UpF4Listenner?) {
        this.upF4Listenner = upF4Listenner
    }
    override fun getLayoutResource(): Int = R.layout.fragment4
    override fun initView(root: View) {
        wdUp = root.findViewById<EditText>(R.id.wd_up)
        wdDown = root.findViewById<EditText>(R.id.wd_down)
        sdUp = root.findViewById<EditText>(R.id.sd_up)
        sdDown = root.findViewById<EditText>(R.id.sd_down)
        twdUp = root.findViewById<EditText>(R.id.twd_up)
        twdDown = root.findViewById<EditText>(R.id.twd_down)
        tsdUp = root.findViewById<EditText>(R.id.tsd_up)
        tsdDown = root.findViewById<EditText>(R.id.tsd_down)
        gzUp1 = root.findViewById<EditText>(R.id.gz_up)
        gzDown1 = root.findViewById<EditText>(R.id.gz_down)
        qyUp1 = root.findViewById<EditText>(R.id.qy_up)
        qyDown1 = root.findViewById<EditText>(R.id.qy_down)
        ylUp1 = root.findViewById<EditText>(R.id.yl_up)
        ylDown1 = root.findViewById<EditText>(R.id.yl_down)
        fsUp1 = root.findViewById<EditText>(R.id.fs_up)
        fsDown1 = root.findViewById<EditText>(R.id.fs_down)
        dlDown = root.findViewById<EditText>(R.id.dl_down)
        save = root.findViewById<TextView>(R.id.save)
        val filters = arrayOf<InputFilter>(MoneyInFilter(activity))
        etTime = root.findViewById<EditText>(R.id.et_time)
        val filters1 = arrayOf<InputFilter>(InFilter(activity))
        etTime!!.filters = filters1
        wdUp!!.filters = filters
        wdDown!!.filters = filters
        sdUp!!.filters = filters
        sdDown!!.filters = filters
        twdUp!!.filters = filters
        twdDown!!.filters = filters
        tsdUp!!.filters = filters
        tsdDown!!.filters = filters
        gzUp1!!.filters = filters
        gzDown1!!.filters = filters
        qyUp1!!.filters = filters
        qyDown1!!.filters = filters
        ylUp1!!.filters = filters
        ylDown1!!.filters = filters
        fsUp1!!.filters = filters
        fsDown1!!.filters = filters
        dlDown!!.filters = filters
        save!!.setOnClickListener {
            val wd = wdUp!!.text.toString()
            val wd1 = wdDown!!.text.toString()
            val sd = sdUp!!.text.toString()
            val sd1 = sdDown!!.text.toString()
            val tw = twdUp!!.text.toString()
            val tw1 = twdDown!!.text.toString()
            val ts = tsdUp!!.text.toString()
            val ts1 = tsdDown!!.text.toString()
            val gz = gzUp1!!.text.toString()
            val gz1 = gzDown1!!.text.toString()
            val qy = qyUp1!!.text.toString()
            val qy1 = qyDown1!!.text.toString()
            val yl = ylUp1!!.text.toString()
            val yl1 = ylDown1!!.text.toString()
            val fs = fsUp1!!.text.toString()
            val fs1 = fsDown1!!.text.toString()
            val dl = dlDown!!.text.toString()

            UserInfo.wdUp = wd
            UserInfo.wdDown = wd1
            UserInfo.sdUp = sd
            UserInfo.sdDown = sd1
            UserInfo.twUp = tw
            UserInfo.twDown = tw1
            UserInfo.tsUp = ts
            UserInfo.tsDown = ts1
            UserInfo.gzUp = gz
            UserInfo.gzDown = gz1
            UserInfo.qyUp = qy
            UserInfo.qyDown = qy1
            UserInfo.ylUp = yl
            UserInfo.ylDown = yl1
            UserInfo.fsUp = fs
            UserInfo.fsDown = fs1
            UserInfo.dlDown = dl
            if (TextUtils.isEmpty(etTime!!.text.toString())) {
                UserInfo.time = 5
            } else {
                UserInfo.time = etTime!!.text.toString().toInt()
            }
            upF4Listenner!!.send4()
        }
    }

    fun isShow(): Boolean {
        return this@Fragment4.isResumed && this@Fragment4.userVisibleHint
    }
}