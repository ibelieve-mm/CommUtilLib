package mm.chenme.lib.commutillibdemo.ui.rare

import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.act_clip_children.*
import kotlinx.android.synthetic.main.act_clip_children.topbar
import kotlinx.android.synthetic.main.act_tab_layout_divider.*
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
class ClipChildrenTestActivity : BaseActivity() {
    override val layoutResId = R.layout.act_clip_children

    override fun initView() {
        initTopBar(topbar,"android:clipChildren=\"false\"")
    }
}