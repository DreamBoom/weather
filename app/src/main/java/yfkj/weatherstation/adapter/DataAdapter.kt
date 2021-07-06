package yfkj.weatherstation.adapter

import android.app.Activity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import yfkj.weatherstation.R
import yfkj.weatherstation.db.Weather


/**
 * @author wmx
 */
class DataAdapter(private val act: Activity, layoutResId: Int, data: List<Weather?>?) :
    BaseQuickAdapter<Weather, BaseViewHolder>(layoutResId, data) {
    override fun convert(holder: BaseViewHolder, data: Weather) {
        holder.setText(R.id.time, data.time)
        holder.setText(R.id.kw, "空温：${data.wd}℃")
        holder.setText(R.id.ks, "空湿：${data.sd}%RH")
        holder.setText(R.id.tw, "土温：${data.tw}℃")
        holder.setText(R.id.ts, "土湿：${data.ts}%RH")
        holder.setText(R.id.gz, "光照：${data.gz}Lux")
        holder.setText(R.id.qy, "气压：${data.qy}Hpa")
        holder.setText(R.id.zyl, "当天雨量：${data.zyl}mm")
        holder.setText(R.id.yl, "雨量：${data.yl}mm/min")
        holder.setText(R.id.fs, "风速：${data.fs}m/s")
        when (data.fx.toInt()) {
            0 -> {
                holder.setText(R.id.fx, "风向：北风")
            }
            90 -> {
                holder.setText(R.id.fx, "风向：东风")
            }
            180 -> {
                holder.setText(R.id.fx, "风向：南风")
            }
            270 -> {
                holder.setText(R.id.fx, "风向：北风")
            }
            360 -> {
                holder.setText(R.id.fx, "风向：西风")
            }
            in 0..90 -> {
                holder.setText(R.id.fx, "风向：东北风")
            }
            in 90..180 -> {
                holder.setText(R.id.fx, "风向：东南风")
            }
            in 180..270 -> {
                holder.setText(R.id.fx, "风向：西南风")
            }
            in 270..359 -> {
                holder.setText(R.id.fx, "风向：西北风")
            }
        }
    }
}