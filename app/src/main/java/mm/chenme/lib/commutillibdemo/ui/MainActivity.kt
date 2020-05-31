package mm.chenme.lib.commutillibdemo.ui

import android.graphics.Color
import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.activity_main.*
import mm.chenme.lib.commutillibdemo.R
import mm.chenme.lib.commutillibdemo.ui.other.AreaSelectActivity
import mm.chenme.lib.commutillibdemo.ui.other.SelfClipLayoutTestActivity
import mm.chenme.lib.commutillibdemo.ui.qmui.QMUIWidgetTestActivity
import mm.chenme.lib.commutillibdemo.ui.rare.ClipToPaddingTestActivity
import mm.chenme.lib.commutillib.BaseActivity
import mm.chenme.lib.commutillib.utils.color
import mm.chenme.lib.commutillibdemo.netdemo.DemoActivity
import mm.chenme.lib.commutillibdemo.ui.other.ShapeRippleButtonActivity
import mm.chenme.lib.commutillibdemo.ui.rare.ClipChildrenTestActivity
import org.jetbrains.anko.startActivity

class MainActivity(
    override val layoutResId: Int = R.layout.activity_main,
    override val isStatusBarLightMode: Boolean = false
) : BaseActivity() {


    override fun initView() {
        topbar.setTitle("主页面").setTextColor(Color.WHITE)
    }

    override fun initListener() {
        qmBtn_qmuiTest.onClick {
//            startActivity<FlutterDemoActivity>()
            startActivity<QMUIWidgetTestActivity>()
        }
        qmBtn_citySelect.onClick { startActivity<AreaSelectActivity>() }
        qmBtn_selfClipLayout.onClick { startActivity<SelfClipLayoutTestActivity>() }
        qmBtn_clipToPadding.onClick { startActivity<ClipToPaddingTestActivity>() }
        qmBtn_clipChildren.onClick { startActivity<ClipChildrenTestActivity>() }
        srb_netDemo.onClick { startActivity<DemoActivity>() }
        srb_shapeRippleButton.onClick { startActivity<ShapeRippleButtonActivity>() }
    }
}
