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

private const val TAG = "---MMLib---"

fun loge(content: String, tag: String = TAG) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, "（${Date().ymdhmsDot()}）>> $content")
        }
    }

    fun logw(content: String, tag: String = TAG) {
        if (BuildConfig.DEBUG) {
            Log.w(tag, "（${Date().ymdhmsDot()}）>> $content")
        }
    }

    fun logd(content: String, tag: String = TAG) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, "（${Date().ymdhmsDot()}）>> $content")
        }
    }

    fun logi(content: String, tag: String = TAG) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, "（${Date().ymdhmsDot()}）>> $content")
        }
    }


    fun logd(content: String, tr: Throwable, tag: String = TAG) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, "（${Date().ymdhmsDot()}）>> $content", tr)
        }
    }