package mm.chenme.lib.commutillibdemo.ui.other

import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.act_shape_ripple_button.topbar
import kotlinx.android.synthetic.main.act_view_stub_test.*
import kotlinx.android.synthetic.main.stub_layout1.*
import mm.chenme.lib.commutillib.BaseActivity
import mm.chenme.lib.commutillib.utils.loge
import mm.chenme.lib.commutillib.utils.st
import mm.chenme.lib.commutillibdemo.R

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/8/5
 * Email：ibelieve1210@163.com
 */
class ViewStubActivity(override val layoutResId: Int = R.layout.act_view_stub_test) : BaseActivity() {

    private var isViewStubInflated = false
    private val sb = StringBuilder()

    override fun initView() {
        initTopBar(topbar, "ViewStub")
        sb.append("vs_placeholder 的ID：${vs_placeholder.id}\n")


    }

    override fun initListener() {
        super.initListener()
        tv_showStub.onClick {
            if (isViewStubInflated) {
                st("ViewStub 已经显示了")
            } else {
                vs_placeholder.inflate()
            }
        }

        vs_placeholder.setOnInflateListener { viewStub, view ->
            isViewStubInflated = true

            sb.append("viewStub 的ID：${viewStub.id}\n")
                .append("ViewStub 已经被填充；\n")
                .append("ViewStub 是否为空：${null == viewStub}\n")
                .append("view 的ID：${view.id}\n")
                .append("cl_stub 的ID：${cl_stub.id}\n")

            tv_tips.text = sb.toString()
            loge(sb.toString())
        }
    }
}