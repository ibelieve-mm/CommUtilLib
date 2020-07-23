package mm.chenme.lib.commutillibdemo.ui.other

import kotlinx.android.synthetic.main.act_shape_ripple_button.*
import mm.chenme.lib.commutillib.BaseActivity
import mm.chenme.lib.commutillibdemo.R

/**
 * Descriptions：
 * <p>
 * Author：ChenME
 * Date：2020/5/31
 * Email：ibelieve1210@163.com
 */
class ShapeRippleButtonActivity(override val layoutResId: Int= R.layout.act_shape_ripple_button) : BaseActivity() {

    override fun initView() {
        initTopBar(topbar,"自定义 Button")
    }
}