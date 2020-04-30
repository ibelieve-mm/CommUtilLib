package mm.chenme.lib.commutillibdemo.utils

import com.qmuiteam.qmui.alpha.QMUIAlphaButton

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020-04-30
 * Email：ibelieve1210@163.com
 */

fun addQMUIBtnAlpha(vararg btns: QMUIAlphaButton){
    btns.forEach {
        it.setChangeAlphaWhenPress(true)
        it.setChangeAlphaWhenDisable(true)
    }
}