package mm.chenme.lib.commutillibdemo.netdemo

import androidx.lifecycle.Observer
import com.qmuiteam.qmui.kotlin.onClick
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog
import kotlinx.android.synthetic.main.act_net_demo.*
import mm.chenme.lib.commutillib.BaseActivity
import mm.chenme.lib.commutillib.utils.bindViewModel
import mm.chenme.lib.commutillibdemo.R


/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/5/29
 * Email：ibelieve1210@163.com
 */
class DemoActivity(override val layoutResId: Int = R.layout.act_net_demo) : BaseActivity() {

    private lateinit var mViewModel: DemoViewModel
    private lateinit var mLoadingDialog: QMUITipDialog

    override fun initData() {
        mViewModel = bindViewModel()
        mViewModel.bindActivity(this)
    }

    override fun initView() {
        mLoadingDialog = QMUITipDialog.Builder(this).setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING).setTipWord("加载中...").create()
        topbar.setTitle("网络请求Demo")
        topbar.addLeftBackImageButton().onClick { closePage() }
    }


    override fun loadData() {
        mLoadingDialog.show()
        mViewModel.queryWeather().observe(this, Observer {
            mLoadingDialog.dismiss()
            it?.apply {
                tv_label.text = this.toString()
            }
        })
    }
}


