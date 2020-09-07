package mm.chenme.lib.commutillibdemo.complex_demo.lottery

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.addListener
import mm.chenme.lib.commutillib.utils.color
import mm.chenme.lib.commutillibdemo.R

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/8/24
 * Email：ibelieve1210@163.com
 */
class LotteryPointerBg @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mPaint: Paint = Paint()
    private val set = AnimatorSet()

    init {
        mPaint.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val size = (if (width < height) width else height).toFloat()
        val centerXY = size / 2

        mPaint.color = Color.WHITE
        canvas.drawCircle(centerXY, centerXY, centerXY, mPaint)
        playAnim()
    }

    fun playAnim() {

        this.alpha = .5f

        val scaleX0 = ObjectAnimator.ofFloat(this, "scaleX", .8f, 1f)
        scaleX0.interpolator = DecelerateInterpolator()
        val scaleY0 = ObjectAnimator.ofFloat(this, "scaleY", .8f, 1f)
        scaleY0.interpolator = DecelerateInterpolator()
        val set0 = AnimatorSet()
        set0.play(scaleX0).with(scaleY0)
        set0.duration = 600
        set0.addListener(onEnd= {  _->
            set.start()
        })
        set0.start()

        val scaleX = ObjectAnimator.ofFloat(this, "scaleX", 1f, .8f, 1f)
        scaleX.interpolator = AccelerateDecelerateInterpolator()
        scaleX.repeatCount = ValueAnimator.INFINITE

        val scaleY = ObjectAnimator.ofFloat(this, "scaleY", 1f, .8f, 1f)
        scaleY.interpolator = AccelerateDecelerateInterpolator()
        scaleY.repeatCount = ValueAnimator.INFINITE
        set.play(scaleX).with(scaleY)
        set.duration = 1200
    }

    fun stopAnim() {

        val alpha = ObjectAnimator.ofFloat(this, "alpha", .5f, 0f)
        alpha.duration = 500
        alpha.start()
        alpha.addListener(onEnd = { _ ->
            set.cancel()
        })
    }
}