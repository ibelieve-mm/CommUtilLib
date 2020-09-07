package mm.chenme.lib.commutillibdemo.complex_demo.lottery

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.core.animation.addListener
import mm.chenme.lib.commutillib.utils.color
import mm.chenme.lib.commutillib.utils.randomIntFromAtoB
import mm.chenme.lib.commutillibdemo.R
import org.jetbrains.anko.dip
import org.jetbrains.anko.sp


/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/8/20
 * Email：ibelieve1210@163.com
 */
class LotteryPan @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    private val mRotateLoading = ObjectAnimator.ofFloat(this, "rotation", 0f, 360f)
    private var mIsInLoading = false

    private var mPaint: Paint = Paint()

    private var mPaintText: Paint = Paint()
    private var mTextSize = sp(12).toFloat()
    private var mTextTopMargin = dip(50).toFloat()
    private val mOutMargin = dip(10).toFloat() // 白圈的宽度

    private var mPreAngle = 0f // 每一份扇形的角度


    private val mBonusList = mutableListOf<BonusBean>()
    private val mColorList = listOf(color(R.color.color_yellow_ffea01), color(R.color.color_yellow_e1ce00))
    private val mImgList = mutableListOf<Bitmap>()
    private val mTextWidthHalfList = mutableListOf<Float>()


    init {
        mPaint.isAntiAlias = true
        mPaintText.isAntiAlias = true
        mPaintText.color = color(R.color.color_red_ff5346)
        mPaintText.textSize = mTextSize
        mPaintText.isFakeBoldText = true

        mRotateLoading.interpolator = LinearInterpolator()
        mRotateLoading.duration = 500
        mRotateLoading.repeatCount = ValueAnimator.INFINITE
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        var size = (if (width < height) width else height).toFloat()
        val circleXY = size / 2f

        mPaint.color = Color.WHITE
        canvas.drawCircle(circleXY, circleXY, circleXY, mPaint)

        size -= mOutMargin

        mPreAngle = 360f / mBonusList.size.toFloat()

        val startAngle = -90f - mPreAngle / 2f
        val rect = RectF(mOutMargin, mOutMargin, size, size)
        val textY = mTextTopMargin + mTextSize

        mBonusList.forEachIndexed { index, bonusBean ->
            mPaint.color = mColorList[index % 2]
            canvas.drawArc(rect, startAngle, mPreAngle, true, mPaint)
            canvas.drawBitmap(mImgList[index], circleXY - dip(20), dip(10).toFloat(), mPaint)
            canvas.drawText(bonusBean.bonusName, circleXY - mTextWidthHalfList[index], textY, mPaintText)
            canvas.rotate(mPreAngle, circleXY, circleXY)
        }
    }

    private fun updateImg() {
        mImgList.clear()
        mTextWidthHalfList.clear()
        mBonusList.forEach {
            mImgList.add(BitmapFactory.decodeResource(resources, it.bonusRes))
            mTextWidthHalfList.add(mPaintText.measureText(it.bonusName) / 2f)
        }
    }

    fun freeBitmap() {
        mImgList.clear()
    }

    fun setBonusList(bonusList: MutableList<BonusBean>) {
        mBonusList.clear()
        mBonusList.addAll(bonusList)
        updateImg()
        invalidate()
    }

    fun playLoadingAnim() {
        mIsInLoading = true
        mRotateLoading.start()
    }


    /**
     * pos: 奖品的位置
     */
    fun playAnim(pos: Int, count: Int = -1, time: Long = 5000, whenAnimEnd: () -> Unit) {

        // 生成一个随机数（0 ~ 每一份扇形扫过的角度）
//        val randomAngle = Math.random().toFloat() * mPreAngle // 随机停留一个位置
        val randomAngle = .5f * mPreAngle // 不使用随机数，停留在正中间

        // 随机生成转动的圈数（-1是默认 3-5 圈）
        val countTmp = if (-1 == count) randomIntFromAtoB(3, 5) else count

        val toAngle = 360f * countTmp - mPreAngle / 2 - (pos - 1) * mPreAngle - randomAngle

        val rotate = ObjectAnimator.ofFloat(this, "rotation", 0f, toAngle)
//        rotate.interpolator = AccelerateDecelerateInterpolator()
        rotate.interpolator = DecelerateInterpolator()
        rotate.duration = time

        rotate.addListener(onEnd = { _ -> whenAnimEnd() })

        if (mIsInLoading) {
            mRotateLoading.cancel()
            mIsInLoading = false
        }
        rotate.start()
    }
}