package yfkj.weatherstation.fragment

import android.view.View
import com.raizlabs.android.dbflow.sql.language.SQLite
import yfkj.weatherstation.R
import yfkj.weatherstation.adapter.DlAdapter
import yfkj.weatherstation.db.Dc
import yfkj.weatherstation.db.Dc_Table
import yfkj.weatherstation.view.MyGridView

class Fragment6 : LazyFragment() {

    var list = ArrayList<String>()
    var gridAdapter: DlAdapter? = null
    override fun getLayoutResource(): Int = R.layout.fragment6
    override fun initView(root: View) {
        val grid = root.findViewById<MyGridView>(R.id.grid)
        gridAdapter = DlAdapter(activity, list, R.layout.dl_item)
        grid!!.adapter = gridAdapter
    }

    fun up(){
        val dc: List<Dc> = SQLite.select()
            .from(Dc::class.java)
            .where()
            .limit(1)//限制条数
            .orderBy(Dc_Table.id, false)//按照升序
            .queryList()
        if(dc.isNotEmpty()){
            list.clear()
            list.add("运行天数:${dc[0].ts}天")
            list.add("蓄电池剩余电量:${dc[0].xdNum}%")
            val xzt = dc[0].xzt
            list.add("蓄电池状态:$xzt")
            list.add("蓄电池电压:${dc[0].xdy}V")
            list.add("蓄电池电流:${dc[0].xdl}A")
            list.add("充电设备状态:${dc[0].czt}")
            list.add("放点设备状态:${dc[0].fzt}")
            list.add("环境温度:${dc[0].hwd}℃")
            list.add("过放次数:${dc[0].gf}")
            list.add("充满次数:${dc[0].cm}")
            list.add("过压保护次数:${dc[0].gy}")
            list.add("过流保护次数:${dc[0].gl}")
            list.add("短路保护次数:${dc[0].ts}")
            list.add("开路保护次数:${dc[0].kl}")
            list.add("硬件保护次数:${dc[0].yj}")
            list.add("充电过温保护次数:${dc[0].cgw}")
            list.add("放电过温保护次数:${dc[0].fgw}")
            list.add("蓄电池功率:${dc[0].xgl}W")
            list.add("负载电压:${dc[0].fdy}V")
            list.add("负载电流:${dc[0].fdl}A")
            list.add("负载功率:${dc[0].fzgl}W")
            list.add("太阳能电压:${dc[0].tdy}V")
            list.add("太阳能电流:${dc[0].tdl}A")
            list.add("发电功率:${dc[0].fdgl}W")
            list.add("当日累计用电量:${dc[0].dayy}KWH")
            list.add("总累计充电量:${dc[0].allc}KWH")
            list.add("总累计用电量:${dc[0].ally}KWH")
            gridAdapter!!.notifyDataSetInvalidated()
        }
    }

    fun isShow(): Boolean {
        return this@Fragment6.isResumed && this@Fragment6.userVisibleHint
    }
}