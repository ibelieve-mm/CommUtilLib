package mm.chenme.lib.commutillibdemo.complex_demo.live_data

import android.content.Context
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
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
 * Date：2020/9/11
 * Email：ibelieve1210@163.com
 */
class LineProgress @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mPaint = Paint()
    private val mPaint2 = Paint()
    private lateinit var mLG: LinearGradient
    private val mSegmentCount = 8
    private val mProgress = .8f

    init {
        mPaint2.color = color(R.color.color_theme)
        mPaint2.isAntiAlias = true
        mPaint2.strokeWidth = dip(1).toFloat()

        mPaint.isAntiAlias = true
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        mLG = LinearGradient(0f, 0f, width * mProgress, height.toFloat(), color(R.color.color_yellow_ffea01), color(R.color.color_red_ff639b), Shader.TileMode.MIRROR)
        mPaint.color = color(R.color.color_theme)
        mPaint.strokeWidth = height.toFloat()
        mPaint.strokeCap = Paint.Cap.ROUND
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawLine(height.toFloat() / 2, height.toFloat() / 2, width.toFloat() - height.toFloat() / 2, height.toFloat() / 2, mPaint)
        mPaint.shader = mLG
        canvas.drawLine(height.toFloat() / 2, height.toFloat() / 2, width * mProgress - height.toFloat() / 2, height.toFloat() / 2, mPaint)


        val preSegment = width / mSegmentCount
        (1 until mSegmentCount).forEach {
            canvas.drawLine(it * preSegment.toFloat(), 0f, it * preSegment.toFloat(), height.toFloat(), mPaint2)
        }
    }
}