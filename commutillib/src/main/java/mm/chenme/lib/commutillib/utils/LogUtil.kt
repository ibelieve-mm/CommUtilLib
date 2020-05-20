package mm.chenme.lib.commutillib.utils

import android.util.Log
import java.util.*

/**
 * Descriptions：Log 工具类
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020-04-29
 * Email：ibelieve1210@163.com
 */

//private const val DEBUG = BuildConfig.DEBUG

private const val TAG = "---MMLib---"

fun loge(content: String, tag: String = TAG) {
    Log.e(tag, "（${Date().ymdhmsDot()}）>> $content")
}

fun logw(content: String, tag: String = TAG) {
    Log.w(tag, "（${Date().ymdhmsDot()}）>> $content")
}

fun logd(content: String, tag: String = TAG) {
    Log.d(tag, "（${Date().ymdhmsDot()}）>> $content")
}

fun logi(content: String, tag: String = TAG) {
    Log.i(tag, "（${Date().ymdhmsDot()}）>> $content")
}


fun logd(content: String, tr: Throwable, tag: String = TAG) {
    Log.d(tag, "（${Date().ymdhmsDot()}）>> $content", tr)
}