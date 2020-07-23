package mm.chenme.lib.commutillibdemo.ui.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.act_fragment_main.*
import kotlinx.android.synthetic.main.act_main.topbar
import mm.chenme.lib.commutillib.BaseActivity
import mm.chenme.lib.commutillibdemo.R

/**
 * Descriptions：Fragment 与 ViewPager2 结合使用示例
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/7/23
 * Email：ibelieve1210@163.com
 */
class FragmentMainActivity(override val layoutResId: Int = R.layout.act_fragment_main) : BaseActivity() {

    private var mLastSelectPageIndex = 0

    private val fList = mutableListOf<Fragment>()
    private val pagerAdapter = object : FragmentStateAdapter(this) {
        override fun getItemCount(): Int {
            return fList.size
        }

        override fun createFragment(position: Int): Fragment {
            return fList[position]
        }
    }

    //region override
    override fun initView() {
        changeCurrentPage()
        initFragment()
    }

    override fun initListener() {
        ti_home.onClick {
            if (0 == mLastSelectPageIndex) {
                return@onClick
            }
            mLastSelectPageIndex = 0
            changeCurrentPage()
        }

        ti_history.onClick {
            if (1 == mLastSelectPageIndex) {
                return@onClick
            }
            mLastSelectPageIndex = 1
            changeCurrentPage()
        }

        ti_third.onClick {
            if (2 == mLastSelectPageIndex) {
                return@onClick
            }
            mLastSelectPageIndex = 2
            changeCurrentPage()
        }

        ti_mine.onClick {
            if (3 == mLastSelectPageIndex) {
                return@onClick
            }
            mLastSelectPageIndex = 3
            changeCurrentPage()
        }
    }
    //endregion

    //region event
    //endregion

    //region private fun
    private fun initFragment() {
        fList.add(HomeFragment())
        fList.add(SecondFragment())
        fList.add(ThirdFragment())
        fList.add(MineFragment())
        ti_home.isSelected = true
        vp.orientation = ViewPager2.ORIENTATION_HORIZONTAL
//        vp.isUserInputEnabled = false // 禁止滑动
        vp.adapter = pagerAdapter
        vp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                mLastSelectPageIndex = position
                changeCurrentPage(false)
            }
        })
    }

    private fun changeCurrentPage(isChangeVPSelectPage: Boolean = true) {
        if (isChangeVPSelectPage) {
            vp.currentItem = mLastSelectPageIndex
        }
        when (mLastSelectPageIndex) {
            0 -> {
                ti_home.isSelected = true
                ti_history.isSelected = false
                ti_third.isSelected = false
                ti_mine.isSelected = false
                topbar.setTitle("home")
//                topbar.setSubTitle(mCurrentDate)
            }
            1 -> {
                ti_home.isSelected = false
                ti_history.isSelected = true
                ti_third.isSelected = false
                ti_mine.isSelected = false
                topbar.setTitle("second")
//                topbar.setSubTitle("")
            }
            2 -> {
                ti_home.isSelected = false
                ti_history.isSelected = false
                ti_third.isSelected = true
                ti_mine.isSelected = false
                topbar.setTitle("third")
//                topbar.setSubTitle("")
            }
            3 -> {
                ti_home.isSelected = false
                ti_history.isSelected = false
                ti_third.isSelected = false
                ti_mine.isSelected = true
                topbar.setTitle("mine")
//                topbar.setSubTitle("")
            }
        }
    }
    //endregion

    //region public fun
    //endregion

    //region net process
    //endregion

    //region class
    //endregion
}