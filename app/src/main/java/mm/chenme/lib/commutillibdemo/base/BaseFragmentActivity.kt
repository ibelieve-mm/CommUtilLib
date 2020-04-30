package mm.chenme.lib.commutillibdemo.base

import android.os.Bundle
import com.qmuiteam.qmui.arch.QMUIFragmentActivity
import com.qmuiteam.qmui.util.QMUIStatusBarHelper

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020-04-30
 * Email：ibelieve1210@163.com
 */
abstract class BaseFragmentActivity : QMUIFragmentActivity() {

    abstract fun loadContentView(): Int
    open fun initData() {}
    open fun initView() {}
    open fun initListener() {}
    open fun loadData() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(loadContentView())

        initData()

        // 设置沉浸模式
        QMUIStatusBarHelper.translucent(this)
        // 设置状态栏黑色字体
        QMUIStatusBarHelper.setStatusBarLightMode(this)
//        // 设置状态栏白色字体
//        QMUIStatusBarHelper.setStatusBarDarkMode(this)

        initView()
        initListener()
        loadData()
    }
}