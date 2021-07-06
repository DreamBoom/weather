package yfkj.weatherstation.fragment

import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.TextView
import yfkj.weatherstation.R
import yfkj.weatherstation.utils.UserInfo
import yfkj.weatherstation.utils.ActivityUtils

class Fragment3 : LazyFragment() {
    var utils: ActivityUtils? = null
    override fun getLayoutResource(): Int = R.layout.fragment3
    var etName: EditText? = null
    var etAddress: EditText? = null

    var upF3Listenner: UpF3Listenner? = null

    interface UpF3Listenner {
        fun send3()
    }

    fun setF3Listenner(upF3Listenner: UpF3Listenner?) {
        this.upF3Listenner = upF3Listenner
    }

    override fun initView(root: View) {
        etName = root.findViewById(R.id.et_name)
        etAddress = root.findViewById(R.id.et_address)
        val save = root.findViewById<TextView>(R.id.save)
        save.setOnClickListener {
            val name = etName!!.text.toString()
            val address = etAddress!!.text.toString()
            if (TextUtils.isEmpty(name)) {
                UserInfo.name = ""
            } else {
                UserInfo.name = name
            }
            if (TextUtils.isEmpty(address)) {
                UserInfo.address = ""
            } else {
                UserInfo.address = address
            }
            upF3Listenner!!.send3()
        }
    }

    fun isShow(): Boolean {
        return this@Fragment3.isResumed && this@Fragment3.userVisibleHint
    }
}