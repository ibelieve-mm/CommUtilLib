package mm.chenme.lib.commutillibdemo.ui

import android.graphics.Color
import android.os.Parcel
import android.text.ParcelableSpan
import android.text.TextPaint
import android.text.TextUtils
import android.text.style.CharacterStyle
import android.text.style.StrikethroughSpan
import android.text.style.UpdateAppearance

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/11/25
 * Email：ibelieve1210@163.com
 */
class MyStrikeThroughSpan(
    private val mColor: Int = Color.RED,
    private val isBold: Boolean = false,
    private val isDelLine: Boolean = false,
    private val isUnderLine: Boolean = false,
) : CharacterStyle(), UpdateAppearance, ParcelableSpan {
    override fun updateDrawState(tp: TextPaint?) {
        tp?.let {
            it.color = mColor
            it.isFakeBoldText = isBold
            it.isStrikeThruText = isDelLine
            it.isSubpixelText = isDelLine
            it.isUnderlineText = isUnderLine
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
    }

    //    override fun writeToParcelInternal(dest: Parcel, flags: Int) {}
    override fun getSpanTypeId(): Int {
        StrikethroughSpan()
        return 5
    }
}