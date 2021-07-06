package yfkj.weatherstation.fragment

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pawegio.kandroid.runOnUiThread
import com.raizlabs.android.dbflow.sql.language.SQLite
import yfkj.weatherstation.R
import yfkj.weatherstation.adapter.DataAdapter
import yfkj.weatherstation.db.Weather
import yfkj.weatherstation.db.Weather_Table

class Fragment2 : LazyFragment(){
    var page = 1
    var pageNum1 = 1
    var All = 1

    companion object {
        @SuppressLint("StaticFieldLeak")
        var dataAdapter: DataAdapter? = null
        @SuppressLint("StaticFieldLeak")
        var list = arrayListOf<Weather>()
        @SuppressLint("StaticFieldLeak")
        var pageNum: TextView? = null
    }
    override fun getLayoutResource(): Int = R.layout.fragment2
    @SuppressLint("SetTextI18n")
    override fun initView(root: View) {
        val pageUp =root.findViewById<TextView>(R.id.pageUp)
        pageNum =root.findViewById(R.id.pageNum)
        val pageDown =root.findViewById<TextView>(R.id.pageDown)
        val dataList =root.findViewById<RecyclerView>(R.id.data_list)
        val ms = LinearLayoutManager(context)
        ms.orientation = LinearLayoutManager.HORIZONTAL
        dataList.layoutManager = ms
        dataAdapter = activity?.let { DataAdapter(it, R.layout.data_item, list) }
        dataList.adapter = dataAdapter
        pageUp.setOnClickListener {
            if (pageNum1 == 1) {
                utils!!.showToast("当前已为最新数据")
                return@setOnClickListener
            }
            val weathers: List<Weather> = SQLite.select()
                .from(Weather::class.java)
                .where()
                .orderBy(Weather_Table.id, false)//按照升序
                .limit(5)//限制条数
                .offset(page - 5)//分页
                .queryList()
            list.clear()
            list.addAll(weathers)
            dataAdapter!!.notifyDataSetChanged()
            page -= 5
            pageNum1 -= 1
            if (All == 1) {
                pageNum!!.text = "1/1"
            } else {
                pageNum!!.text = "$pageNum1/$All"
            }
        }
        pageDown.setOnClickListener {
            if (pageNum1 >= All) {
                utils!!.showToast("暂无更多数据")
                return@setOnClickListener
            }
            val weathers: List<Weather> = SQLite.select()
                .from(Weather::class.java)
                .where()
                .orderBy(Weather_Table.id, false)//按照升序
                .limit(5)//限制条数
                .offset(page + 5)//分页
                .queryList()
            list.clear()
            list.addAll(weathers)
            dataAdapter!!.notifyDataSetChanged()
            page += 5
            pageNum1 += 1
            if (All == 1) {
                pageNum!!.text = "1/1"
            } else {
                pageNum!!.text = "$pageNum1/$All"
            }
        }
        up()
    }

    @SuppressLint("SetTextI18n")
    fun up(){
        val weathers1: List<Weather> = SQLite.select()
            .from(Weather::class.java)
            .where()
            .orderBy(Weather_Table.id, false)//按照升序
            .queryList()
        if(weathers1.isEmpty()){
            return
        }
        when(weathers1.size){
            1 ->{
                list.clear()
                list.add(weathers1[0])
            }
            2->{
                list.clear()
                list.add(weathers1[0])
                list.add(weathers1[1])
            }
            3->{
                list.clear()
                list.add(weathers1[0])
                list.add(weathers1[1])
                list.add(weathers1[2])
            }
            4->{
                list.clear()
                list.add(weathers1[0])
                list.add(weathers1[1])
                list.add(weathers1[2])
                list.add(weathers1[3])
            }
            else->{
                list.clear()
                list.add(weathers1[0])
                list.add(weathers1[1])
                list.add(weathers1[2])
                list.add(weathers1[3])
                list.add(weathers1[4])
            }
        }
        if(weathers1.size>5){
            All = weathers1.size / 5
        }

        runOnUiThread {
            dataAdapter!!.notifyDataSetChanged()
            if (pageNum1 == 0 || All == 1) {
                pageNum!!.text = "1/1"
            } else {
                pageNum!!.text = "$pageNum1/$All"
            }
        }
    }

    fun isShow():Boolean{
        return  this@Fragment2.isResumed && this@Fragment2.userVisibleHint
    }
}