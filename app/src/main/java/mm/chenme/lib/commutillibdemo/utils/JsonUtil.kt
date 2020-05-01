package mm.chenme.lib.commutillibdemo.utils

import com.google.gson.Gson

/**
 * Descriptions：
 * <p>
 * Author：ChenME
 * Date：2020/5/1
 * Email：ibelieve1210@163.com
 */

fun <T> parseJson(json: String, type: Class<T>): T {
    return Gson().fromJson(json, type)
}