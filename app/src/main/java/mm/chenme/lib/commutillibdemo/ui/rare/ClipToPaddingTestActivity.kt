package mm.chenme.lib.commutillibdemo.ui.rare

import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.act_clip_to_padding.*
import kotlinx.android.synthetic.main.act_clip_to_padding.topbar
import kotlinx.android.synthetic.main.act_tab_layout_divider.*
import mm.chenme.lib.commutillib.pro.adapter.BaseRecyclerViewAdapter
import mm.chenme.lib.commutillib.utils.color
import mm.chenme.lib.commutillibdemo.R
import mm.chenme.lib.commutillib.BaseActivity

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/5/22
 * Email：ibelieve1210@163.com
 */
class ClipToPaddingTestActivity : BaseActivity() {
    override val layoutResId: Int = R.layout.act_clip_to_padding

    override fun initView() {
        initTopBar(topbar,"android:clipToPadding=\"false\"")

        val list = IntRange(1, 100).step(2)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = BaseRecyclerViewAdapter(this, list.toMutableList(), R.layout.list_city_item) { rootView, dataItem, pos ->
            (rootView as TextView).text = "当前数据是 --> $dataItem"
            rootView.setBackgroundColor(color(R.color.color_theme_t10))
        }
    }
}