package mm.chenme.lib.commutillibdemo.complex_demo.lottery

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import mm.chenme.lib.commutillib.utils.color
import mm.chenme.lib.commutillibdemo.R
import org.jetbrains.anko.dip

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/8/24
 * Email：ibelieve1210@163.com
 */
class LotteryPointer @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mPaint: Paint = Paint()

    init {
        mPaint.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val size = (if (width < height) width else height).toFloat()
        val centerXY = size / 2

        val path = Path()
        path.moveTo(centerXY, 0f)
        path.lineTo(centerXY + dip(20), dip(35).toFloat())
        path.lineTo(centerXY - dip(20), dip(35).toFloat())
        path.close()
        mPaint.color = Color.WHITE
        canvas.drawPath(path, mPaint)

        val path2 = Path()
        path2.moveTo(centerXY, dip(10).toFloat())
        path2.lineTo(centerXY + dip(15), dip(35).toFloat())
        path2.lineTo(centerXY - dip(15), dip(35).toFloat())
        path2.close()
        mPaint.color = color(R.color.color_red_ff467d)
        canvas.drawPath(path2, mPaint)

//        mPaint.color = Color.WHITE
//        canvas.drawCircle(centerXY, centerXY, (size - dip(40)) / 2f, mPaint)
//        mPaint.color = color(R.color.color_red_ff467d)
//        canvas.drawCircle(centerXY, centerXY, (size - dip(50)) / 2f, mPaint)
    }
}