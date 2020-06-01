package mm.chenme.lib.commutillibdemo.utils

import mm.chenme.lib.commutillib.utils.appVersionCode
import mm.chenme.lib.commutillibdemo.BuildConfig
import mm.cme.commnetlib.RetrofitClient

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/6/1
 * Email：ibelieve1210@163.com
 */

fun <T> createService(clazz: Class<T>, url: String? = null, headers: Map<String, String>? = null): T = RetrofitClient.build(url ?: BuildConfig.API_HOST, headers ?: mutableMapOf<String, String>().apply {
    this["versionCode"] = appVersionCode.toString()
}).create(clazz)