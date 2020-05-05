package mm.chenme.lib.commutillibdemo.ui

import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.activity_main.*
import mm.chenme.lib.commutillibdemo.R
import mm.chenme.lib.commutillibdemo.base.BaseFragmentActivity
import org.jetbrains.anko.startActivity

class MainActivity : BaseFragmentActivity() {

    override fun loadContentView(): Int = R.layout.activity_main

    override fun initView() {

        topbar.setTitle("主页面")

    }

    override fun initListener() {
        qmBtn_qmuiTest.onClick {
//            startActivity<FlutterDemoActivity>()
            startActivity<QMUIWidgetTestActivity>()
        }

        qmBtn_citySelect.onClick {
            startActivity<AreaSelectActivity>()
        }
    }
}
