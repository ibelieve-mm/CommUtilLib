package mm.chenme.lib.commutillibdemo.ui.other

import kotlinx.android.synthetic.main.act_self_clip_layout_test.*
import mm.chenme.lib.commutillib.BaseActivity
import mm.chenme.lib.commutillibdemo.R

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/5/22
 * Email：ibelieve1210@163.com
 */
class SelfClipLayoutTestActivity : BaseActivity() {
    override val layoutResId: Int = R.layout.act_self_clip_layout_test

    override fun initView() {
        initTopBar(topbar,"自定义 ClipLayout")
    }
}