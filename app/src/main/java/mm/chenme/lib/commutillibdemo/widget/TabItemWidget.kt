package mm.chenme.lib.commutillibdemo.widget

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.ui_tab.view.*
import mm.chenme.lib.commutillib.utils.color
import mm.chenme.lib.commutillib.utils.show
import mm.chenme.lib.commutillibdemo.R

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/7/14
 * Email：ibelieve1210@163.com
 */

class TabItemWidget @JvmOverloads constructor(
    private val mContext: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(mContext, attrs, defStyleAttr) {

    private var ti_icon = android.R.drawable.ic_dialog_email
    private var ti_title = ""

    init {

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TabItemWidget)
        ti_icon = typedArray.getResourceId(R.styleable.TabItemWidget_ti_icon, ti_icon)
        ti_title = typedArray.getString(R.styleable.TabItemWidget_ti_title) ?: ti_title

        initView()
        typedArray.recycle()

    }

    private fun initView() {
        View.inflate(context, R.layout.ui_tab, this)

        iv_icon.show(ti_icon)
        tv_title.text = ti_title

        val states = arrayOf(intArrayOf(android.R.attr.state_selected), intArrayOf())
        val colors = intArrayOf(color(R.color.color_theme), color(R.color.color_theme_t40))
        val colorStateList = ColorStateList(states, colors)
        iv_icon.imageTintList = colorStateList
        tv_title.setTextColor(colorStateList)
    }
}