package mm.chenme.lib.commutillib.utils

import android.os.Handler
import android.os.Looper
import android.widget.Toast

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020-04-30
 * Email：ibelieve1210@163.com
 */

private var toast: Toast? = null // Toast对象
private val handler = Handler(Looper.getMainLooper())

/**
 * 短 Toast
 */
fun st(messageRes: Int) {
    mt(string(messageRes))
}

fun st(message: String) {
    mt(message)
}

/**
 * 长 Toast
 */
fun lt(messageRes: Int) {
    mt(string(messageRes), true)
}

fun lt(message: String) {
    mt(message, true)
}

fun mt(msg: String, isLongToast: Boolean = false) {
    Thread(Runnable {
        run {
            handler.post {
                synchronized(AppGlobals.application()) {
                    if (toast != null) {
                        toast!!.cancel()
                        toast = null
                    }
                    toast = Toast.makeText(AppGlobals.application(), msg, if (isLongToast) Toast.LENGTH_LONG else Toast.LENGTH_SHORT)
                    toast!!.show()
                }
            }
        }
    }).start()
}