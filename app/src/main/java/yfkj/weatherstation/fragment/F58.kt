package yfkj.weatherstation.fragment

import android.annotation.SuppressLint
import android.graphics.DashPathEffect
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.pawegio.kandroid.runOnUiThread
import com.raizlabs.android.dbflow.sql.language.SQLite
import yfkj.weatherstation.R
import yfkj.weatherstation.db.Weather
import yfkj.weatherstation.db.Weather_Table

class F58 : LazyFragment(){
    override fun getLayoutResource(): Int = R.layout.f58
    var lineChart:LineChart?=null
    var time:TextView?=null
    override fun initView(root: View) {
        lineChart = root.findViewById(R.id.chart)
        time = root.findViewById(R.id.time1)
        setData()
    }

    var set1: LineDataSet? = null
    @SuppressLint("SetTextI18n")
    fun setData() {
        lineChart!!.setDrawGridBackground(true)
        lineChart!!.description.isEnabled = true
        lineChart!!.setTouchEnabled(false)
        lineChart!!.isDragEnabled = false
        lineChart!!.setScaleEnabled(true)
        lineChart!!.setPinchZoom(true)
        runOnUiThread {
            lineChart!!.animateX(1500)
        }
        val l: Legend = lineChart!!.legend
        l.form = Legend.LegendForm.NONE
        //这里我模拟一些数据
        val values = arrayListOf<Entry>()
        values.clear()
        val weathers: List<Weather> = SQLite.select()
            .from(Weather::class.java)
            .where()
            .orderBy(Weather_Table.id, false)
            .limit(10)//限制条数
            .queryList()
        if(weathers.size<2){
            return
        }
        val reversed = weathers.reversed()
        runOnUiThread {
            time!!.text = "日期 ${reversed[0].time} 至 ${reversed[reversed.size - 1].time} 单位:m/s"
        }

        val stList = arrayListOf<String>()
        for (i in reversed.indices) {
            stList.add(reversed[i].time.split(" ")[1])
            try {
                values.add(Entry(i.toFloat(), reversed[i].fs.toFloat()))
            } catch (e: Exception) {
                values.add(Entry(0L.toFloat(), reversed[i].fs.toFloat()))
            }
        }

        val xAxis: XAxis = lineChart!!.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.textSize = 10f
        xAxis.textColor = context?.let { ContextCompat.getColor(it, R.color.blue) }!!
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(true)
        xAxis.labelCount = 9
        val valueFormatter: ValueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return if (value >= 0) {
                    stList[value.toInt() % stList.size]
                } else {
                    ""
                }
            }
        }
        xAxis.valueFormatter = valueFormatter
        set1 = LineDataSet(values, "")
        set1!!.mode = LineDataSet.Mode.CUBIC_BEZIER
        set1!!.enableDashedLine(10f, 5f, 0f)
        set1!!.enableDashedHighlightLine(10f, 5f, 0f)
        set1!!.color = context?.let { ContextCompat.getColor(it, R.color.black3) }!!
        set1!!.setCircleColor(ContextCompat.getColor(context!!, R.color.black3))
        set1!!.lineWidth = 1f
        set1!!.circleRadius = 3f
        set1!!.setDrawCircleHole(false)
        set1!!.valueTextSize = 9f
        set1!!.setDrawFilled(true)
        set1!!.formLineWidth = 1f
        set1!!.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
        set1!!.formSize = 15f
        lineChart!!.data = LineData(set1)
        lineChart!!.data.notifyDataChanged()
        lineChart!!.notifyDataSetChanged()
    }


    fun isShow():Boolean{
        return  this@F58.isResumed && this@F58.userVisibleHint
    }
}