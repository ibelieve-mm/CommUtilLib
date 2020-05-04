package mm.chenme.lib.commutillib.pro.view_exts

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import mm.chenme.lib.commutillib.pro.view_exts.ViewClipHelper.setViewOutline

/**
 * Descriptions：
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
        ViewClipHelper.setViewOutline(this, attrs, defStyleAttr, 0);
    }

    fun setViewOutline(radius: Int, radiusSide: Int) {
        setViewOutline(this, radius, radiusSide)
    }
}