package mm.chenme.lib.commutillibdemo.ui.qmui

import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.activity_qmui_widget_test.*
import mm.chenme.lib.commutillib.utils.color
import mm.chenme.lib.commutillib.utils.loge
import mm.chenme.lib.commutillibdemo.R
import mm.chenme.lib.commutillib.BaseActivity
import mm.chenme.lib.commutillib.utils.stoast
import mm.chenme.lib.commutillibdemo.utils.addQMUIBtnAlpha
import org.jetbrains.anko.dip

/**
 * Descriptions：QMUI 控件测试
 * <p>
 * Author：ChenME
 * Date：2020/5/2
 * Email：ibelieve1210@163.com
 */
class QMUIWidgetTestActivity : BaseActivity() {

    override val layoutResId: Int = R.layout.activity_qmui_widget_test

    override fun initView() {

        topbar.setTitle("QMUI 控件测试")
        topbar.setSubTitle("这里是副标题")
        topbar.addLeftBackImageButton().onClick { closePage() }

        addQMUIBtnAlpha(btn_printLog, qmBtn_cannotUse, qmBtn_showToast, qmBtn_noEvent)

        qmll_test.setRadiusAndShadow(dip(10), dip(14), .7f)
        qmll_test.shadowColor = color(R.color.color_red_ff639b)

        qmcl_test.setRadiusAndShadow(dip(20), dip(14), .7f)
        qmcl_test.shadowColor = color(R.color.color_theme)
    }

    override fun initListener() {
        btn_printLog.onClick {
            loge("打印 log 日志！")
        }

        qmBtn_showToast.onClick {
            stoast("弹出 toast！")
        }
    }
}