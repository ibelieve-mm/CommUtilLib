package mm.chenme.lib.commutillibdemo.complex_demo.surface_view

import kotlinx.android.synthetic.main.act_surface_view.*
import mm.chenme.lib.commutillib.BaseActivity
import mm.chenme.lib.commutillibdemo.R

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/8/5
 * Email：ibelieve1210@163.com
 */
class SurfaceViewActivity(override val layoutResId: Int = R.layout.act_surface_view) : BaseActivity() {

    override fun initView() {
        initTopBar(topbar, "SurfaceView")

    }
}
