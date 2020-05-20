package mm.chenme.lib.commutillib.pro.view_exts

import android.annotation.TargetApi
import android.content.res.TypedArray
import android.graphics.Outline
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider
import mm.chenme.lib.commutillib.R


/**
 * Descriptions：用于裁剪圆角布局的 Helper
 * <p>
 * Author：ChenME
 * Date：2020/5/4
 * Email：ibelieve1210@163.com
 */
object  ViewClipHelper {

    private const val RADIUS_ALL = 0
    private const val RADIUS_LEFT = 1
    private const val RADIUS_TOP = 2
    private const val RADIUS_RIGHT = 3
    private const val RADIUS_BOTTOM = 4

    fun setViewOutline(view: View, attributes: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        val array: TypedArray = view.context.obtainStyledAttributes(attributes, R.styleable.ViewClipHelperStrategy, defStyleAttr, defStyleRes)
        val radius = array.getDimensionPixelSize(R.styleable.ViewClipHelperStrategy_clip_radius, 0)
        val hideSide = array.getInt(R.styleable.ViewClipHelperStrategy_clip_side, 0)
        array.recycle()
        setViewOutline(view, radius, hideSide)
    }

    /**
     * 设置 view 带有边角的边线
     * @param owner view
     * @param radius 半径
     * @param radiusSide view 的边角
     */
    fun setViewOutline(owner: View, radius: Int, radiusSide: Int) {
        owner.outlineProvider = object : ViewOutlineProvider() {
            @TargetApi(21)
            override fun getOutline(view: View, outline: Outline) {
                val w: Int = view.width
                val h: Int = view.height
                if (w == 0 || h == 0) {
                    return
                }
                //某一边需要圆角
                if (radiusSide != RADIUS_ALL) {
                    var left = 0
                    var top = 0
                    var right = w
                    var bottom = h
                    when (radiusSide) {
                        RADIUS_LEFT -> { right += radius }
                        RADIUS_TOP -> { bottom += radius }
                        RADIUS_RIGHT -> { left -= radius }
                        RADIUS_BOTTOM -> { top -= radius }
                    }
                    outline.setRoundRect(left, top, right, bottom, radius.toFloat())
                    return
                }
                //所有边需要圆角
                val top = 0
                val left = 0
                if (radius <= 0) {
                    outline.setRect(left, top, w, h)
                } else {
                    outline.setRoundRect(left, top, w, h, radius.toFloat())
                }
            }
        }
        owner.clipToOutline = radius > 0
        owner.invalidate()
    }
}