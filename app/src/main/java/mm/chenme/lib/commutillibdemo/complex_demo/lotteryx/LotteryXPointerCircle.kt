package mm.chenme.lib.commutillibdemo.complex_demo.lotteryx

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_UP
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.FrameLayout
import androidx.core.animation.addListener
import mm.chenme.lib.commutillibdemo.R


/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/8/24
 * Email：ibelieve1210@163.com
 */
class LotteryXPointerCircle @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var onClick: (() -> Unit)? = null
    private var isFinishing = true
    private var isAlreadyUp = false


    init {
        View.inflate(context, R.layout.ui_lotteryx_pointer_circle, this)
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            ACTION_DOWN -> {
                if (isEnabled) {
                    shrink()
                }
            }
            ACTION_UP -> {
                if (isEnabled) {
                    onClick?.let { it() }
                    isAlreadyUp = true
                    if (isFinishing) {
                        zoom()
                    }
                }
            }
        }
        return super.onTouchEvent(event)
    }


    private fun zoom() {
        val scaleX = ObjectAnimator.ofFloat(this, "scaleX", .9f, 1f)
        scaleX.interpolator = AccelerateDecelerateInterpolator()

        val scaleY = ObjectAnimator.ofFloat(this, "scaleY", .9f, 1f)
        scaleY.interpolator = AccelerateDecelerateInterpolator()

        val set = AnimatorSet()
        set.play(scaleX).with(scaleY)
        set.duration = 100
        set.addListener(onEnd = { _ ->
            isAlreadyUp = false
        })
        set.start()
    }

    private fun shrink() {
        val scaleX = ObjectAnimator.ofFloat(this, "scaleX", 1f, .9f)
        scaleX.interpolator = AccelerateDecelerateInterpolator()

        val scaleY = ObjectAnimator.ofFloat(this, "scaleY", 1f, .9f)
        scaleY.interpolator = AccelerateDecelerateInterpolator()

        val set = AnimatorSet()
        set.play(scaleX).with(scaleY)
        set.duration = 100
        set.addListener(onStart = { _ ->
            isFinishing = false
        }, onEnd = { _ ->
            isFinishing = true
            if (isAlreadyUp) {
                zoom()
            }
        })
        set.start()
    }

    fun setClick(click: () -> Unit) {
        onClick = click
    }
}