package mm.chenme.lib.commutillib.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.ImageSpan
import android.widget.TextView
import java.lang.ref.WeakReference

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/8/28
 * Email：ibelieve1210@163.com
 */

/**
 * 替换TextView中的指定文字为drawable,所有指定target替换
 */
fun replaceTextWithDrawable(textView: TextView, target: String, id: Int, width: Int, height: Int) {
    if (id == 0 || TextUtils.isEmpty(target) || TextUtils.isEmpty(textView.text)) return
    val spannableString = SpannableString(textView.text)
    val drawable = textView.resources.getDrawable(id)
    drawable.setBounds(0, 0, width, height)
    var start = 0
    while (textView.text.toString().indexOf(target, start).also { start = it } != -1) {
        spannableString.setSpan(CenteredImageSpan(drawable), start, start + target.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        start += target.length
    }
    textView.text = spannableString
}

/**
 * 支持居中显示
 */
class CenteredImageSpan : ImageSpan {
    private var mDrawableRef: WeakReference<Drawable?>? = null

    internal constructor(context: Context?, drawableRes: Int) : super(context!!, drawableRes) {}
    constructor(d: Drawable) : super(d) {}

    override fun draw(
        canvas: Canvas, text: CharSequence,
        start: Int, end: Int, x: Float,
        top: Int, y: Int, bottom: Int, paint: Paint
    ) {
        val b = cachedDrawable
        canvas.save()
        val transY = top + (bottom - top - b!!.bounds.bottom) / 2
        canvas.translate(x, transY.toFloat())
        b.draw(canvas)
        canvas.restore()
    }

    // Redefined locally because it is a private member from DynamicDrawableSpan
    private val cachedDrawable: Drawable?
        private get() {
            val wr = mDrawableRef
            var d: Drawable? = null
            if (wr != null) d = wr.get()
            if (d == null) {
                d = drawable
                mDrawableRef = WeakReference(d)
            }
            return d
        }
}