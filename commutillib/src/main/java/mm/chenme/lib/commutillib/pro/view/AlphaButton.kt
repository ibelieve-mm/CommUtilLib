package mm.chenme.lib.commutillib.pro.view

import android.content.Context
import android.util.AttributeSet
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/5/29
 * Email：ibelieve1210@163.com
 */
class AlphaButton : QMUIRoundButton {
    constructor(context: Context) : super(context) {
        initView(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context, attrs, defStyleAttr)
    }

    private fun initView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        setChangeAlphaWhenDisable(true)
        setChangeAlphaWhenPress(true)
    }
}