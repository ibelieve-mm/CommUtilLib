package mm.chenme.lib.commutillibdemo.ui

import android.os.Bundle
import com.qmuiteam.qmui.arch.QMUIFragmentActivity
import com.qmuiteam.qmui.kotlin.onClick
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import kotlinx.android.synthetic.main.activity_main.*
import mm.chenme.lib.commutillib.utils.Lt
import mm.chenme.lib.commutillibdemo.R

class MainActivity : QMUIFragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 设置状态栏黑色字体
        QMUIStatusBarHelper.setStatusBarLightMode(this)


        tv_hw.onClick {
            Lt.e("设置状态栏黑色字体")
            Lt.e("设置状态栏黑色字体","ChenME")
        }
    }
}
