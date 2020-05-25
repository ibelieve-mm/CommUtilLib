package mm.chenme.lib.commutillib.utils

import android.content.Context
import android.view.inputmethod.InputMethodManager

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/5/25
 * Email：ibelieve1210@163.com
 */

/**
 * 获取 InputMethodManager
 */
val Context.inputMethodManager: InputMethodManager get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager