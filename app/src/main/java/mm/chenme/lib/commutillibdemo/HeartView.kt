package mm.chenme.lib.commutillibdemo

import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import mm.chenme.lib.commutillib.utils.loge
import org.jetbrains.anko.dip


/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/6/9
 * Email：ibelieve1210@163.com
 */

class HeartView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val mPaint = Paint()
    private val mPath = Path()
    private val mHeartPointList = mutableListOf<PointF>()
    private val mCirclePointList = mutableListOf<PointF>()
    private val mCurPointList = mutableListOf<PointF>()

    private var mWidth = 0f
    private var mHeight = 0f

    private var mIsInit = false

    private var mAnimator: ValueAnimator

    init {
        mPaint.isAntiAlias = true
        mPaint.color = Color.parseColor("#ff639b")
        mPaint.style = Paint.Style.FILL


        mHeartPointList.add(PointF(0f, dip(-38).toFloat()))
        mHeartPointList.add(PointF(dip(50).toFloat(), dip(-103).toFloat()))
        mHeartPointList.add(PointF(dip(112).toFloat(), dip(-61).toFloat()))
        mHeartPointList.add(PointF(dip(112).toFloat(), dip(-12).toFloat()))
        mHeartPointList.add(PointF(dip(112).toFloat(), dip(37).toFloat()))
        mHeartPointList.add(PointF(dip(51).toFloat(), dip(90).toFloat()))
        mHeartPointList.add(PointF(0f, dip(129).toFloat()))
        mHeartPointList.add(PointF(dip(-51).toFloat(), dip(90).toFloat()))
        mHeartPointList.add(PointF(dip(-112).toFloat(), dip(37).toFloat()))
        mHeartPointList.add(PointF(dip(-112).toFloat(), dip(-12).toFloat()))
        mHeartPointList.add(PointF(dip(-112).toFloat(), dip(-61).toFloat()))
        mHeartPointList.add(PointF(dip(-50).toFloat(), dip(-103).toFloat()))

        mCirclePointList.add(PointF(0f, dip(-89).toFloat()))
        mCirclePointList.add(PointF(dip(50).toFloat(), dip(-89).toFloat()))
        mCirclePointList.add(PointF(dip(90).toFloat(), dip(-49).toFloat()))
        mCirclePointList.add(PointF(dip(90).toFloat(), 0f))
        mCirclePointList.add(PointF(dip(90).toFloat(), dip(50).toFloat()))
        mCirclePointList.add(PointF(dip(50).toFloat(), dip(90).toFloat()))
        mCirclePointList.add(PointF(0f, dip(90).toFloat()))
        mCirclePointList.add(PointF(dip(-49).toFloat(), dip(90).toFloat()))
        mCirclePointList.add(PointF(dip(-89).toFloat(), dip(50).toFloat()))
        mCirclePointList.add(PointF(dip(-89).toFloat(), 0f))
        mCirclePointList.add(PointF(dip(-89).toFloat(), dip(-49).toFloat()))
        mCirclePointList.add(PointF(dip(-49).toFloat(), dip(-89).toFloat()))

        mCurPointList.add(PointF(0f, dip(-89).toFloat()))
        mCurPointList.add(PointF(dip(50).toFloat(), dip(-89).toFloat()))
        mCurPointList.add(PointF(dip(90).toFloat(), dip(-49).toFloat()))
        mCurPointList.add(PointF(dip(90).toFloat(), 0f))
        mCurPointList.add(PointF(dip(90).toFloat(), dip(50).toFloat()))
        mCurPointList.add(PointF(dip(50).toFloat(), dip(90).toFloat()))
        mCurPointList.add(PointF(0f, dip(90).toFloat()))
        mCurPointList.add(PointF(dip(-49).toFloat(), dip(90).toFloat()))
        mCurPointList.add(PointF(dip(-89).toFloat(), dip(50).toFloat()))
        mCurPointList.add(PointF(dip(-89).toFloat(), 0f))
        mCurPointList.add(PointF(dip(-89).toFloat(), dip(-49).toFloat()))
        mCurPointList.add(PointF(dip(-49).toFloat(), dip(-89).toFloat()))

        mAnimator = ValueAnimator.ofFloat(0f, 1f)
        mAnimator.duration = 15000
        mAnimator.addUpdateListener(AnimatorUpdateListener { animation ->
            val x = animation.animatedValue as Float
            val factor = 0.15f
            // 动画：http://inloop.github.io/interpolator/
            // factor = 0.4
            // pow(2, -10 * x) * sin((x - factor / 4) * (2 * PI) / factor) + 1

            val value = Math.pow(2.0, -10 * x.toDouble()) * Math.sin((x - factor / 4) * (2 * Math.PI) / factor) + 1
            for (i in mCurPointList.indices) {
                val startPoint = mCirclePointList[i]
                val endPoint = mHeartPointList[i]
                mCurPointList[i].x = startPoint.x + ((endPoint.x - startPoint.x) * value).toFloat()
                mCurPointList[i].y = startPoint.y + ((endPoint.y - startPoint.y) * value).toFloat()
            }
            postInvalidate()
        })
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (!mIsInit) {
            mWidth = measuredWidth.toFloat()
            mHeight = measuredHeight.toFloat()
            mIsInit = true
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.translate(mWidth / 2, mHeight / 2)
        mPath.reset()
        for (i in 0..3) {
            if (i == 0) {
                mPath.moveTo(mCurPointList[i * 3].x, mCurPointList[i * 3].y)
            } else {
                mPath.lineTo(mCurPointList[i * 3].x, mCurPointList[i * 3].y)
            }
            val endPointIndex: Int = if (i == 3) 0 else i * 3 + 3
            mPath.cubicTo(
                mCurPointList[i * 3 + 1].x, mCurPointList[i * 3 + 1].y,
                mCurPointList[i * 3 + 2].x, mCurPointList[i * 3 + 2].y,
                mCurPointList[endPointIndex].x, mCurPointList[endPointIndex].y
            )
        }

        canvas.drawPath(mPath, mPaint)
    }

    fun start() {
        if (mAnimator.isRunning) {
            return
        }
        mAnimator.start()
    }

}