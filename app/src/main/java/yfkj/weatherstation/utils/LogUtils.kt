package yfkj.weatherstation.utils

import android.util.Log

object LogUtils {
    /**
     * 截断输出日志
     * @param msg
     */
    fun i(msg: String) {
        var msg = msg
        val segmentSize = 3 * 1024
        val length = msg.length.toLong()
        if (length <= segmentSize) {
            Log.i("========>", msg)
        } else {
            while (msg.length > segmentSize) {
                val logContent = msg.substring(0, segmentSize)
                msg = msg.replace(logContent, "")
                Log.i("========>", logContent)
            }
            Log.i("========>", msg)
        }
    }

    fun i(msg: Int) {
        Log.i("========>", "" + msg)
    }
}