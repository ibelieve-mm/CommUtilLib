package mm.chenme.lib.commutillib.exts

import android.content.Context
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
fun Context.stoast(messageRes: Int) {
    this.mtoast(this.getString(messageRes).toString())
}

fun Context.stoast(message: String) {
    this.mtoast(message)
}

/**
 * 长 Toast
 */
fun Context.ltoast(messageRes: Int) {
    this.mtoast(this.getString(messageRes).toString(), true)
}

fun Context.ltoast(message: String) {
    this.mtoast(message, true)
}

fun Context.mtoast(msg: String, isLongToast: Boolean = false) {
    Thread(Runnable {
        run {
            handler.post {
                synchronized(this) {
                    if (toast != null) {
                        toast!!.cancel()
                        toast = null
                    }
                    toast = Toast.makeText(this, msg, if (isLongToast) Toast.LENGTH_LONG else Toast.LENGTH_SHORT)
                    toast!!.show()
                }
            }
        }
    }).start()
}