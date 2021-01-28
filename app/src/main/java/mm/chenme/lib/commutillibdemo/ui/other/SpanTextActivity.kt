package mm.chenme.lib.commutillibdemo.ui.other

import android.graphics.Color
import android.text.Spannable
import android.text.Spanned
import android.widget.TextView
import kotlinx.android.synthetic.main.act_bezier_heart_view.topbar
import kotlinx.android.synthetic.main.act_span_text.*
import mm.chenme.lib.commutillib.BaseActivity
import mm.chenme.lib.commutillibdemo.R
import mm.chenme.lib.commutillibdemo.ui.MyStrikeThroughSpan

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/11/25
 * Email：ibelieve1210@163.com
 */
class SpanTextActivity(override val layoutResId: Int = R.layout.act_span_text) : BaseActivity() {
    override fun initView() {
        initTopBar(topbar, "TextView 文字特殊处理")

        val text = "小盆友，你是不是有很多问？"
        tv1.setText(text, TextView.BufferType.SPANNABLE)
        val spannable = tv1.text as Spannable
        spannable.setSpan(MyStrikeThroughSpan(Color.MAGENTA,true,true,true), 3, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }

}