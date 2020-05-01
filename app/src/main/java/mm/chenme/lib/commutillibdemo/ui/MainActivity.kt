package mm.chenme.lib.commutillibdemo.ui

import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.activity_main.*
import mm.chenme.lib.commutillib.exts.stoast
import mm.chenme.lib.commutillib.utils.loge
import mm.chenme.lib.commutillibdemo.R
import mm.chenme.lib.commutillibdemo.base.BaseFragmentActivity
import mm.chenme.lib.commutillibdemo.utils.addQMUIBtnAlpha
import org.jetbrains.anko.startActivity

class MainActivity : BaseFragmentActivity() {

    override fun loadContentView(): Int = R.layout.activity_main

    override fun initView() {

        topbar.setTitle("主页面")
        topbar.setSubTitle("副标题")

        topbar.addLeftBackImageButton()

        addQMUIBtnAlpha(btn_printLog, qmBtn_cannotUse, qmBtn_showToast, qmBtn_noEvent)
    }

    override fun initListener() {
        btn_printLog.onClick {
            loge("打印 log 日志！")

            startActivity<AreaSelectActivity>()
        }
        qmBtn_showToast.onClick {
            stoast("弹出 toast！")
        }
    }
}
