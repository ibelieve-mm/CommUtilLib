package mm.chenme.lib.commutillibdemo.complex_demo.lotteryx

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import android.widget.TextView
import android.widget.ViewSwitcher
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.addListener
import com.qmuiteam.qmui.kotlin.dip
import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.act_lotteryx_view.*
import mm.chenme.lib.commutillib.utils.*
import mm.chenme.lib.commutillibdemo.R
import mm.chenme.lib.commutillibdemo.complex_demo.lottery.BonusBean


/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/8/20
 * Email：ibelieve1210@163.com
 */
class LotteryXViewActivity : AppCompatActivity() {

    private val mBonusSize = 9
    private var resultIndex = 4

    private val mIconList = mutableListOf(R.mipmap.gems_40, R.mipmap.off_40, R.mipmap.plus_40, R.mipmap.points_40)

    private val mViewFactory = ViewSwitcher.ViewFactory {
        val result = TextView(this)
        result.textSize = 16f
        result.setTextColor(color(R.color.color_yellow_ffe300))
        result
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_lotteryx_view)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        initView()
    }

    private fun initView() {

        ts_gems.setFactory(mViewFactory)
        ts_points.setFactory(mViewFactory)
        ts_off.setFactory(mViewFactory)

        val bonusList = mutableListOf<BonusBean>()
        (0..mBonusSize).forEach { i ->
            bonusList.add(BonusBean("1$i Days", mIconList[i % 4]))
        }
        val lp = lotteryView.layoutParams
        lp.height = screenWidthPx - dip(13) * 2
        lotteryView.layoutParams = lp

        tv_lotteryPrice.text = "8[bcoins]/time"
        replaceTextWithDrawable(tv_lotteryPrice, "[bcoins]", R.mipmap.coins_20, dip(20), dip(20))
        ts_gems.setCurrentText("x 123")
        ts_points.setCurrentText("x 123")
        ts_off.setCurrentText("30%")
        tv_bubble.text = "180:80"
        updateOffCountDownPosition()

        lotteryView.setBonusList(bonusList)
        lotteryView.onLotteryViewEvent(object : LotteryXView.OnLotteryViewEvent {
            override fun onPointerClick(): Boolean {
                btn_fill.setVisible(true)
                btn_err.setVisible(true)
                return true
            }

            override fun onPanStop(resultBonusIndex: Int) {
                stoast("!!恭喜获得奖品$resultBonusIndex")
            }

            override fun onPanStopWithErr() {
                toggleErrTips(false)
            }

            override fun onBonusBackgroundAnimEnter() {
                resultIconEnter()
            }

            override fun onBonusBackgroundAnimExit() {
                resultIconExit(iv_iconPoints)
            }
        })

        btn_fill.onClick {
            btn_fill.setVisible(false)
            btn_err.setVisible(false)
            lotteryView.lotteryStartWithOK(resultIndex)
        }

        btn_err.onClick {
            btn_fill.setVisible(false)
            btn_err.setVisible(false)
            lotteryView.lotteryStartWithErr()
            toggleErrTips(true)
        }
    }

    /**
     * show lottery result ICON
     */
    fun resultIconEnter() {
        tv_resultText.text = "x 123"
        iv_resultIcon.setImageResource(R.mipmap.points_120)
        tv_resultText.setVisible(true)
        iv_resultIcon.setVisible(true)
        val scaleX = ObjectAnimator.ofFloat(iv_resultIcon, "scaleX", 0f, 1f)
        val scaleY = ObjectAnimator.ofFloat(iv_resultIcon, "scaleY", 0f, 1f)
        val scaleX1 = ObjectAnimator.ofFloat(tv_resultText, "scaleX", 0f, 1f)
        val scaleY1 = ObjectAnimator.ofFloat(tv_resultText, "scaleY", 0f, 1f)

        interpolatorAnim(OvershootInterpolator(), scaleX, scaleY, scaleX1, scaleY1)
        val set = AnimatorSet()
        set.play(scaleX).with(scaleY).with(scaleX1).with(scaleY1)
        set.duration = 300
        set.start()
    }

    /**
     * exit lottery result ICON
     */
    fun resultIconExit(to: AppCompatImageView) {

        // result text anim
        val scaleX1 = ObjectAnimator.ofFloat(tv_resultText, "scaleX", 1f, 0f)
        val scaleY1 = ObjectAnimator.ofFloat(tv_resultText, "scaleY", 1f, 0f)
        val set1 = AnimatorSet()
        set1.play(scaleX1).with(scaleY1)
        set1.duration = 300
        set1.start()
        set1.addListener(onEnd = { _ -> tv_resultText.setVisible(false) })

        // result icon anim
        val scale = ScaleAnimation(1f, .3333f, 1f, .3333f)
        val globalRect = Rect()
        iv_resultIcon.getGlobalVisibleRect(globalRect)
        val globalRectTarget = Rect()
        to.getGlobalVisibleRect(globalRectTarget)

        val translate = TranslateAnimation(0f, (globalRectTarget.left - globalRect.left).toFloat(), 0f, (globalRectTarget.top - globalRect.top).toFloat())

        val set = AnimationSet(true)
        set.addAnimation(scale)
        set.addAnimation(translate)
        set.duration = 600
        set.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                iv_resultIcon.setVisible(false)
                ts_off.setText("text")
            }

            override fun onAnimationStart(animation: Animation?) {}
        })
        iv_resultIcon.startAnimation(set)
    }

    /**
     * 切换 err tips
     */
    private fun toggleErrTips(isShow: Boolean) {
        if (isShow) {
            fl_errTips.setVisible(true)
        }
        val alpha = AlphaAnimation(if (isShow) 0f else 1f, if (isShow) 1f else 0f)
        alpha.duration = 300
        alpha.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                if (isShow) {
//                    Handler().postDelayed(Runnable {
//                        toggleErrTips(false)
//                    }, 2000)
                } else {
                    fl_errTips.setVisible(false)
                }
            }

            override fun onAnimationStart(animation: Animation?) {}
        })
        fl_errTips.startAnimation(alpha)
    }

    /**
     * add interpolator for objAnim
     */
    private fun interpolatorAnim(interpolator: BaseInterpolator, vararg objAnims: ObjectAnimator) {
        objAnims.forEach { it.interpolator = interpolator }
    }


    /**
     * update 倒计时的位置
     */
    private fun updateOffCountDownPosition() {
        if (View.GONE == tv_bubble.visibility) {
            tv_bubble.setVisible(true)
        }
        ts_off.post {
            val location = IntArray(2)
            ts_off.getLocationOnScreen(location)
            val layoutParams = tv_bubble.layoutParams as ConstraintLayout.LayoutParams
            layoutParams.marginStart = location[0] + ts_off.width / 2 - tv_bubble.width / 2 - dip(8)
            tv_bubble.layoutParams = layoutParams
        }
    }
}