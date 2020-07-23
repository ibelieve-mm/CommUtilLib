package mm.chenme.lib.commutillibdemo.ui.bezier

import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.act_bezier_heart_view.*
import kotlinx.android.synthetic.main.act_bezier_heart_view.topbar
import kotlinx.android.synthetic.main.act_main.*
import mm.chenme.lib.commutillib.BaseActivity
import mm.chenme.lib.commutillibdemo.R

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/6/12
 * Email：ibelieve1210@163.com
 */

class BezierHeartViewTestActivity(override val layoutResId: Int = R.layout.act_bezier_heart_view) : BaseActivity() {

    override fun initView() {
        initTopBar(topbar, "贝塞尔曲线 demo")
    }

    override fun initListener() {
        tv_play.onClick { heartView.start() }
    }
}