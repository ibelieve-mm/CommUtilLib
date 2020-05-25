package mm.chenme.lib.commutillib.utils

import android.app.Activity
import android.view.View

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/5/25
 * Email：ibelieve1210@163.com
 */

/**
 * 隐藏键盘
 */
fun Activity.hideKeyboard(view: View? = null) {
    if (view != null) {
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    } else {
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}