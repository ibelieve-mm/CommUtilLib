package mm.chenme.lib.commutillib.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/6/5
 * Email：ibelieve1210@163.com
 */

fun Context.getSP(name: String = "mm.commlib", mode: Int = Context.MODE_PRIVATE): SharedPreferences = getSharedPreferences(name, mode)

fun SharedPreferences.bool(key: String, default: Boolean = false): Boolean = getBoolean(key, default)
fun SharedPreferences.int(key: String, default: Int = 0): Int = getInt(key, default)
fun SharedPreferences.long(key: String, default: Long = 0L): Long = getLong(key, default)
fun SharedPreferences.float(key: String, default: Float = 0f): Float = getFloat(key, default)
fun SharedPreferences.string(key: String, default: String = ""): String = getString(key, default)!!
fun SharedPreferences.stringSet(key: String, default: Set<String> = mutableSetOf()): Set<String> = getStringSet(key, default)!!

/*

// 存储
getSP().edit { putString(SPConst.UserToken, "小盆友，你是不是有很多问？") }

// 读取
getSP().string(SPConst.UserToken)
 */