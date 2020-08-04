package mm.chenme.lib.commutillibdemo.complex_demo.rv_paging_scroll

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.act_grid_pager_snap_helper.*
import mm.chenme.lib.commutillib.BaseActivity
import mm.chenme.lib.commutillib.pro.adapter.BaseRecyclerViewAdapter
import mm.chenme.lib.commutillib.utils.screenWidthPx
import mm.chenme.lib.commutillib.utils.show
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
        topbar.setTitle("分页滚动")
        topbar.addLeftBackImageButton().onClick { closePage() }

        val columns = 3
        val rows = 2
        val layoutManager = GridLayoutManager(this, rows, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager

        val helper = GridPagerSnapHelper(layoutManager)
        helper.setColumn(columns).setRow(rows)
        helper.attachToRecyclerView(recyclerView)

        val list = mutableListOf<Int>()
        IntRange(1, 20).forEach { list.add(it) }
        recyclerView.adapter = BaseRecyclerViewAdapter(this, transformAndFillEmptyData(list, rows, columns), R.layout.list_grid_pager_snap_helper_item) { rootView, dataItem, pos ->

            if (null == dataItem) {
                rootView.find<AppCompatTextView>(R.id.tv_itemName).text = ""
                rootView.find<AppCompatImageView>(R.id.iv_itemIcon).setImageDrawable(resources.getDrawable(android.R.color.transparent))
            } else {
                rootView.find<AppCompatTextView>(R.id.tv_itemName).text = "数据：$dataItem"
                rootView.find<AppCompatImageView>(R.id.iv_itemIcon).show(R.mipmap.tmp128)
            }

            val lp = rootView.layoutParams
            lp.width = screenWidthPx / columns
            rootView.layoutParams = lp
        }
    }
}
