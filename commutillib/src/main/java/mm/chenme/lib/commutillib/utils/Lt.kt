package mm.chenme.lib.commutillib.utils

import android.util.Log
import mm.chenme.lib.commutillib.BuildConfig
import mm.chenme.lib.commutillib.exts.ymdhmsDot
import java.util.*

/**
 * Descriptions：Log 工具类
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020-04-29
 * Email：ibelieve1210@163.com
 */
object Lt {
    private const val TAG = "---MMLib---"

    fun e(content: String, tag: String = TAG) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, "（${showNow()}）>> $content")
        }
    }

    fun w(content: String, tag: String = TAG) {
        if (BuildConfig.DEBUG) {
            Log.w(tag, "（${showNow()}）>> $content")
        }
    }

    fun d(content: String, tag: String = TAG) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, "（${showNow()}）>> $content")
        }
    }

    fun i(content: String, tag: String = TAG) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, "（${showNow()}）>> $content")
        }
    }


    fun d(content: String, tr: Throwable, tag: String = TAG) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, "（${showNow()}）>> $content", tr)
        }
    }

    private fun showNow(): String {
        return Date().ymdhmsDot()
    }
}