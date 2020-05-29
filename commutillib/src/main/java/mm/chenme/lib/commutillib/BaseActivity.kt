package mm.chenme.lib.commutillib

import android.os.Bundle
import com.qmuiteam.qmui.arch.QMUIFragmentActivity
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import mm.chenme.lib.commutillib.utils.loge

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020-04-30
 * Email：ibelieve1210@163.com
 */
abstract class BaseActivity : QMUIFragmentActivity() {

    abstract val layoutResId: Int // 页面布局资源ID
    open val isStatusBarLightMode: Boolean = true // 是否显示浅色状态栏模式(即：状态栏背景：浅色，字体：黑色)

    open fun initData() {} // 初始化数据
    open fun initView() {} // 初始化View
    open fun initListener() {} // 初始化Listener
    open fun loadData() {} // 请求数据

    fun dataEmpty(){
        loge("-------- BaseActivity ------------> 数据为空")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)

        initData()

        QMUIStatusBarHelper.translucent(this) // 设置沉浸模式
        if (isStatusBarLightMode) { // 设置状态栏黑色字体
            QMUIStatusBarHelper.setStatusBarLightMode(this)
        } else { // 设置状态栏白色字体
            QMUIStatusBarHelper.setStatusBarDarkMode(this)
        }

        initView()
        initListener()
        loadData()
    }

    fun closePage() {
        onBackPressed()
    }
}