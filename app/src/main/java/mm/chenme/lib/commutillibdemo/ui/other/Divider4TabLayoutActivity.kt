package mm.chenme.lib.commutillibdemo.ui.other

import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.act_tab_layout_divider.*
import mm.chenme.lib.commutillib.BaseActivity
import mm.chenme.lib.commutillibdemo.R


/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/7/23
 * Email：ibelieve1210@163.com
 */
class Divider4TabLayoutActivity(override val layoutResId: Int = R.layout.act_tab_layout_divider) : BaseActivity() {

    override fun initView() {
        super.initView()

        initTopBar(topbar,"TabLayoutWithDivider")

        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        tabLayout.addTab(tabLayout.newTab().setText("1"))
        tabLayout.addTab(tabLayout.newTab().setText("2"))
        tabLayout.addTab(tabLayout.newTab().setText("3"))
        tabLayout.addTab(tabLayout.newTab().setText("4"))
        tabLayout.addTab(tabLayout.newTab().setText("5"))
        tabLayout.addTab(tabLayout.newTab().setText("Holla Hi"))

        for (i in 0 until tabLayout.tabCount) {
            val tab = tabLayout.getTabAt(i)
            val relativeLayout = LayoutInflater.from(this).inflate(R.layout.layout_tab_item, tabLayout, false) as RelativeLayout
            if (i == 0) {
                relativeLayout.findViewById<View>(R.id.view_tabDivider).visibility = View.INVISIBLE
            }
            relativeLayout.findViewById<TextView>(R.id.tv_tabTitle).text = tab!!.text
            tab.customView = relativeLayout
            tab.select()
        }
        tabLayout.getTabAt(0)?.select()
    }
}