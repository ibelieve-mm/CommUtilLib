package mm.chenme.lib.commutillib.pro.view

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import mm.chenme.lib.commutillib.pro.view.ViewClipHelper.setViewOutline

/**
 * Descriptions：可裁剪为圆角的 ConstraintLayout 布局
 * <p>
 * Author：ChenME
 * Date：2020/5/4
 * Email：ibelieve1210@163.com
 */
class ClipConstraintLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        ViewClipHelper.setViewOutline(this, attrs, defStyleAttr, 0)
    }

    fun setViewOutline(radius: Int, radiusSide: Int) {
        setViewOutline(this, radius, radiusSide)
    }
}