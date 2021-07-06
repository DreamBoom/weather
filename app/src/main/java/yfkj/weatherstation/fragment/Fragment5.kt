package yfkj.weatherstation.fragment

import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import yfkj.weatherstation.R
import yfkj.weatherstation.adapter.LazyFragmentAdapter
import yfkj.weatherstation.view.CustomViewPager

class Fragment5 : LazyFragment(){
    override fun getLayoutResource(): Int = R.layout.fragment5

    var f1: TextView? = null
    var f2: TextView? = null
    var f3: TextView? = null
    var f4: TextView? = null
    var f5: TextView? = null
    var f6: TextView? = null
    var f7: TextView? = null
    var f8: TextView? = null
    private val fragments: ArrayList<Fragment> = ArrayList()
    fun setData(){
        val tb1 = fragments[0] as F51
        val tb2 = fragments[1] as F52
        val tb3 = fragments[2] as F53
        val tb4 = fragments[3] as F54
        val tb5 = fragments[4] as F55
        val tb6 = fragments[5] as F56
        val tb7 = fragments[6] as F57
        val tb8 = fragments[7] as F58
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
    override fun initView(root: View) {
        f1 = root.findViewById(R.id.f51)
        f2 = root.findViewById(R.id.f52)
        f3 = root.findViewById(R.id.f53)
        f4 = root.findViewById(R.id.f54)
        f5 = root.findViewById(R.id.f55)
        f6 = root.findViewById(R.id.f56)
        f7 = root.findViewById(R.id.f57)
        f8 = root.findViewById(R.id.f58)
        val pager = root.findViewById<CustomViewPager>(R.id.f5_pager)
        val adapter =
            LazyFragmentAdapter(childFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        fragments.add(F51())
        fragments.add(F52())
        fragments.add(F53())
        fragments.add(F54())
        fragments.add(F55())
        fragments.add(F56())
        fragments.add(F57())
        fragments.add(F58())
        adapter.items = fragments
        pager.adapter = adapter
        f1!!.setOnClickListener {
            pager.currentItem = 0
            bac()
            f1!!.setBackgroundResource(R.drawable.bg_blue)
            f1!!.setTextColor(resources.getColor(R.color.white))
        }

        f2!!.setOnClickListener {
            pager.currentItem = 1
            bac()
            f2!!.setBackgroundResource(R.drawable.bg_blue)
            f2!!.setTextColor(resources.getColor(R.color.white))
        }
        f3!!.setOnClickListener {
            pager.currentItem = 2
            bac()
            f3!!.setBackgroundResource(R.drawable.bg_blue)
            f3!!.setTextColor(resources.getColor(R.color.white))
        }

        f4!!.setOnClickListener {
            pager.currentItem = 3
            bac()
            f4!!.setBackgroundResource(R.drawable.bg_blue)
            f4!!.setTextColor(resources.getColor(R.color.white))
        }

        f5!!.setOnClickListener {
            pager.currentItem = 4
            bac()
            f5!!.setBackgroundResource(R.drawable.bg_blue)
            f5!!.setTextColor(resources.getColor(R.color.white))
        }

        f6!!.setOnClickListener {
            pager.currentItem = 5
            bac()
            f6!!.setBackgroundResource(R.drawable.bg_blue)
            f6!!.setTextColor(resources.getColor(R.color.white))
        }

        f7!!.setOnClickListener {
            pager.currentItem = 6
            bac()
            f7!!.setBackgroundResource(R.drawable.bg_blue)
            f7!!.setTextColor(resources.getColor(R.color.white))
        }

        f8!!.setOnClickListener {
            pager.currentItem = 7
            bac()
            f8!!.setBackgroundResource(R.drawable.bg_blue)
            f8!!.setTextColor(resources.getColor(R.color.white))
        }
    }
    private fun bac() {
        f1!!.setBackgroundResource(R.drawable.bg_btngrey)
        f2!!.setBackgroundResource(R.drawable.bg_btngrey)
        f3!!.setBackgroundResource(R.drawable.bg_btngrey)
        f4!!.setBackgroundResource(R.drawable.bg_btngrey)
        f5!!.setBackgroundResource(R.drawable.bg_btngrey)
        f6!!.setBackgroundResource(R.drawable.bg_btngrey)
        f7!!.setBackgroundResource(R.drawable.bg_btngrey)
        f8!!.setBackgroundResource(R.drawable.bg_btngrey)
        f1!!.setTextColor(resources.getColor(R.color.grey91))
        f2!!.setTextColor(resources.getColor(R.color.grey91))
        f3!!.setTextColor(resources.getColor(R.color.grey91))
        f4!!.setTextColor(resources.getColor(R.color.grey91))
        f5!!.setTextColor(resources.getColor(R.color.grey91))
        f6!!.setTextColor(resources.getColor(R.color.grey91))
        f7!!.setTextColor(resources.getColor(R.color.grey91))
        f8!!.setTextColor(resources.getColor(R.color.grey91))
    }
    fun isShow():Boolean{
        return  this@Fragment5.isResumed && this@Fragment5.userVisibleHint
    }
}