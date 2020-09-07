package mm.chenme.lib.commutillib.utils

import android.view.View
import android.widget.EditText

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/6/2
 * Email：ibelieve1210@163.com
 */

fun View.setVisible(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun EditText.catchInt(default: Int = 0): Int {
    if (null == this.text) {
        return default
    }
    return this.text.toString().toIntNoErr(default)
}