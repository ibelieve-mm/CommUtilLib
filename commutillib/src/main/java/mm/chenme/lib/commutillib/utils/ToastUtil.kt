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
fun stoast(messageRes: Int) {
    mtoast(string(messageRes))
}

fun stoast(message: String) {
    mtoast(message)
}

/**
 * 长 Toast
 */
fun ltoast(messageRes: Int) {
    mtoast(string(messageRes), true)
}

fun ltoast(message: String) {
    mtoast(message, true)
}

fun mtoast(msg: String, isLongToast: Boolean = false) {
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