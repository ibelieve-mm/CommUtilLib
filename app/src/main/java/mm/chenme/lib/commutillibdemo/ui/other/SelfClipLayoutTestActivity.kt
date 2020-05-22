package mm.chenme.lib.commutillibdemo.ui.other

import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.activity_self_clip_layout_test.*
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
class SelfClipLayoutTestActivity : BaseFragmentActivity() {
    override fun loadContentView(): Int = R.layout.activity_self_clip_layout_test

    override fun initView() {
        topbar.setTitle("自定义 ClipLayout")
        topbar.addLeftBackImageButton().onClick { closePage() }
    }
}