package mm.chenme.lib.commutillibdemo.ui.rare

import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.activity_clip_to_padding.*
import mm.chenme.lib.commutillib.pro.adapter.BaseRecyclerViewAdapter
import mm.chenme.lib.commutillib.utils.color
import mm.chenme.lib.commutillibdemo.R
import mm.chenme.lib.commutillibdemo.base.BaseFragmentActivity

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/5/22
 * Email：ibelieve1210@163.com
 */
class ClipToPaddingTestActivity : BaseFragmentActivity() {
    override fun loadContentView(): Int = R.layout.activity_clip_to_padding

    override fun initView() {

        topbar.setTitle("android:clipToPadding=\"false\"")
        topbar.addLeftBackImageButton().onClick { closePage() }

        val list = IntRange(1, 100).step(2)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = BaseRecyclerViewAdapter(this, list.toMutableList(), R.layout.list_city_item) { rootView, dataItem, pos ->
            (rootView as TextView).text = "当前数据是 --> $dataItem"
            rootView.setBackgroundColor(color(R.color.color_theme_t10))
        }
    }
}