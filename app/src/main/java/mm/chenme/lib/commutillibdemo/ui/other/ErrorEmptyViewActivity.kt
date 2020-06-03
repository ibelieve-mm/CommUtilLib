package mm.chenme.lib.commutillibdemo.ui.other

import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.act_error_empty_view.*
import mm.chenme.lib.commutillib.BaseActivity
import mm.chenme.lib.commutillib.utils.stoast
import mm.chenme.lib.commutillibdemo.R

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/6/2
 * Email：ibelieve1210@163.com
 */

class ErrorEmptyViewActivity(override val layoutResId: Int = R.layout.act_error_empty_view) : BaseActivity() {

    override fun initView() {
        topbar.setTitle("Error Empty Loading View")
        topbar.addLeftBackImageButton().onClick { closePage() }
        errView.loadContentView(tv_contentArea)
    }

    override fun initListener() {
        tv_showContentBtn.onClick { errView.showContent() }
        tv_showLoadingBtn.onClick { errView.showLoading() }
        tv_showEmptyBtn.onClick { errView.showEmpty() }
        tv_showErrorBtn.onClick { errView.showError(retryText = "点我重试") { stoast("重新加载") } }
    }


}