package mm.chenme.lib.commutillib.utils

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.text.TextPaint
import android.view.View
import android.widget.EditText
import android.widget.TextView

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/6/2
 * Email：ibelieve1210@163.com
 */

fun View.setVisible(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun EditText.catchInt(default: Int = 0): Int {
    if (null == this.text) {
        return default
    }
    return this.text.toString().toIntNoErr(default)
}

/**
 * 对文字进行渐变色处理
 */
fun TextView.linearGradient(startColor: Int, endColor: Int) {
    this.viewTreeObserver.addOnPreDrawListener {
        val paint: TextPaint = this.paint
        val linearGradient = LinearGradient(0f, 0f, this.measuredWidth.toFloat(), this.measuredHeight.toFloat(), startColor, endColor, Shader.TileMode.CLAMP)
        paint.shader = linearGradient
        true
    }
    /*
    tv_test.linearGradient(Color.parseColor("#FF639B"), Color.parseColor("#1b85ed"))
     */
}

fun TextView.linearGradient(colors: IntArray, positions: FloatArray? = null) {
    this.viewTreeObserver.addOnPreDrawListener {
        val paint: TextPaint = this.paint
//        val colors = intArrayOf(Color.RED, Color.GREEN, Color.BLUE)
//        val positions = floatArrayOf(0f, .5f, 1f)
        val linearGradient = LinearGradient(0f, 0f, this.measuredWidth.toFloat(), this.measuredHeight.toFloat(), colors, positions, Shader.TileMode.CLAMP)
        paint.shader = linearGradient
        true
    }
    /*
    val colors = intArrayOf(Color.RED, Color.GREEN, Color.BLUE)
    val positions = floatArrayOf(0f, .2f, 1f)
    tv_test.linearGradient(colors, positions)
     */
}