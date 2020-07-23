package mm.chenme.lib.commutillib.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes

/**
 * Descriptions：资源工具类
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/5/20
 * Email：ibelieve1210@163.com
 */

//fun Context.dp2px(value: Int): Int = (value * resources.displayMetrics.density + .5f).toInt()
//fun Context.dp2px(value: Float): Int = (value * resources.displayMetrics.density + .5f).toInt()
//
//fun Context.sp2px(value: Int): Int = (value * resources.displayMetrics.scaledDensity + .5f).toInt()
//fun Context.sp2px(value: Float): Int = (value * resources.displayMetrics.scaledDensity + .5f).toInt()
//
//fun Context.px2dp(value: Int): Float = value.toFloat() / resources.displayMetrics.density
//fun Context.px2sp(value: Int): Float = value.toFloat() / resources.displayMetrics.scaledDensity

/**
 * 屏幕宽
 * 2020/5/20，ChenME
 * @return 屏幕宽 px
 */
val screenWidthPx get() = AppGlobals.application().resources.displayMetrics.widthPixels

/**
 * 屏幕高
 * 2020/5/20，ChenME
 * @return 屏幕高 px
 */
val screenHeightPx get() = AppGlobals.application().resources.displayMetrics.heightPixels

//fun inflate(@LayoutRes resId: Int, parent: ViewGroup? = null, attachToRoot: Boolean = false): View = LayoutInflater.from(AppGlobals.application()).inflate(resId, parent, attachToRoot)
fun Context.inflate(@LayoutRes resId: Int, parent: ViewGroup? = null, attachToRoot: Boolean = false): View = LayoutInflater.from(this).inflate(resId, parent, attachToRoot)

fun string(@StringRes resId: Int): String = AppGlobals.application().getString(resId)
fun string(@StringRes resId: Int, vararg formatArgs: Any): String = AppGlobals.application().getString(resId, formatArgs)

fun color(@ColorRes resId: Int): Int = AppGlobals.application().resources.getColor(resId)

//val Context.screenWidthPx get() = resources.displayMetrics.widthPixels
//val Context.screenHeightPx get() = resources.displayMetrics.heightPixels
//
//fun Context.inflate(@LayoutRes resId: Int, parent: ViewGroup? = null, attachToRoot: Boolean = false): View = LayoutInflater.from(this).inflate(resId, parent, attachToRoot)
//
//fun Context.string(@StringRes resId: Int): String =getString(resId)
//fun Context.string(@StringRes resId: Int, vararg formatArgs: Any): String = getString(resId, formatArgs)
//
//fun Context.color(@ColorRes resId: Int): Int = resources.getColor(resId)