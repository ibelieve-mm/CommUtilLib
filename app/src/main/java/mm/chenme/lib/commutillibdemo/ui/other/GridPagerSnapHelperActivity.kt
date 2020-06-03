package mm.chenme.lib.commutillibdemo.ui.other

import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.viewpager2.widget.ViewPager2
import com.qmuiteam.qmui.kotlin.dip
import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.act_grid_pager_snap_helper.*
import mm.chenme.lib.commutillib.BaseActivity
import mm.chenme.lib.commutillib.pro.adapter.BaseRecyclerViewAdapter
import mm.chenme.lib.commutillib.utils.screenWidthPx
import mm.chenme.lib.commutillibdemo.R
import org.jetbrains.anko.find


/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/6/3
 * Email：ibelieve1210@163.com
 */
class GridPagerSnapHelperActivity(override val layoutResId: Int = R.layout.act_grid_pager_snap_helper) : BaseActivity() {

    override fun initView() {
        topbar.setTitle("Error Empty Loading View")
        topbar.addLeftBackImageButton().onClick { closePage() }

//        val layoutManager = GridLayoutManager(this, 3)
        val layoutManager = GridLayoutManager(this, 2, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager

        val helper = GridPagerSnapHelper(layoutManager)
        helper.setColumn(3).setRow(2)
        helper.attachToRecyclerView(recyclerView)

//        PagerSnapHelper().attachToRecyclerView(recyclerView)

        val list = mutableListOf<Int>()
        IntRange(1, 50).forEach { list.add(it) }
        recyclerView.adapter = BaseRecyclerViewAdapter(this, list, R.layout.list_grid_pager_snap_helper_item) { rootView, dataItem, pos ->
            rootView.find<AppCompatTextView>(R.id.tv_itemName).text = "$pos -> $dataItem"

            val lp = rootView.layoutParams
//            lp.width = (screenWidthPx-dip(20))/3
            lp.width = screenWidthPx / 3
            rootView.layoutParams = lp
        }
    }
}