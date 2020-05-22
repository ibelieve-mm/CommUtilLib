package mm.chenme.lib.commutillibdemo.ui.rare

import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.activity_clip_children.*
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
class ClipChildrenTestActivity : BaseFragmentActivity() {
    override fun loadContentView(): Int = R.layout.activity_clip_children

    override fun initView() {
        topbar.setTitle("android:clipChildren=\"false\"")
        topbar.addLeftBackImageButton().onClick { closePage() }
    }
}