package mm.chenme.lib.commutillibdemo.complex_demo.lotteryx

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.FrameLayout
import androidx.core.animation.addListener
import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.ui_lotteryx_view.view.*
import mm.chenme.lib.commutillib.utils.setVisible
import mm.chenme.lib.commutillibdemo.R
import mm.chenme.lib.commutillibdemo.complex_demo.lottery.BonusBean

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/8/26
 * Email：ibelieve1210@163.com
 */
class LotteryXView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var mOnLotteryViewEvent: OnLotteryViewEvent? = null
    private val mBonusList = mutableListOf<BonusBean>()

    init {
        initView()
    }

    private fun initView() {
        View.inflate(context, R.layout.ui_lotteryx_view, this)
        lPointerCircle.setClick {
            if (null != mOnLotteryViewEvent && mOnLotteryViewEvent!!.onPointerClick()) {
                lPointerBg.stopAnim()
                lPointerCircle.isEnabled = false
                lPan.playLoadingAnim()
//                lPan.playAnim(mResultBonusIndex) {
//                    lPointerBg.playAnim()
//                    mOnLotteryViewEvent!!.onPanStop(mResultBonusIndex)
//                    playBonusBgAnimEnter()
//                }
            }
        }
        lPointerCircle.onClick { }
    }

    private fun playBonusBgAnimExit() {
        val alpha = ObjectAnimator.ofFloat(view_bonusBg, "alpha", 1f, 0f)
        alpha.duration = 300
        alpha.addListener(onStart = { _ ->
            mOnLotteryViewEvent?.onBonusBackgroundAnimExit()
        }, onEnd = { _ ->
            view_bonusBg.setVisible(false)
            lPointerCircle.isEnabled = true
        })
        alpha.start()
    }

    private fun playBonusBgAnimEnter() {
        view_bonusBg.setVisible(true)
        view_bonusBg.alpha = 1f
        val scaleX = ObjectAnimator.ofFloat(view_bonusBg, "scaleX", 0f, 1f)
        scaleX.interpolator = OvershootInterpolator()
        val scaleY = ObjectAnimator.ofFloat(view_bonusBg, "scaleY", 0f, 1f)
        scaleY.interpolator = OvershootInterpolator()
        val set = AnimatorSet()
        set.play(scaleX).with(scaleY)
        set.duration = 300
        set.addListener(onStart = { _ ->
            mOnLotteryViewEvent?.onBonusBackgroundAnimEnter()
        }, onEnd = { _ ->
            Handler().postDelayed(Runnable {
                playBonusBgAnimExit()
            }, 2000)
        })
        set.start()
    }

    fun setBonusList(bonusList: MutableList<BonusBean>) {
        mBonusList.clear()
        mBonusList.addAll(bonusList)
        lPan.setBonusList(mBonusList)
    }

    fun onLotteryViewEvent(onLotteryViewEvent: OnLotteryViewEvent) {
        mOnLotteryViewEvent = onLotteryViewEvent
    }

//    fun showFreeTag(isShow: Boolean = true) {
//        tv_isFree.setVisible(isShow)
//    }

    fun lotteryStartWithOK(resultBonusIndex: Int) {
        lPan.playAnim(resultBonusIndex) {
            lPointerBg.playAnim()
            mOnLotteryViewEvent!!.onPanStop(resultBonusIndex)
            playBonusBgAnimEnter()
        }
    }

    fun lotteryStartWithErr() {
        lPan.playAnim(0, 2, 2000) {
            lPointerBg.playAnim()
            lPointerCircle.isEnabled = true
            mOnLotteryViewEvent!!.onPanStopWithErr()
        }
    }

    fun freeResources() {
        lPan.freeBitmap()
    }

    interface OnLotteryViewEvent {
        fun onPointerClick(): Boolean
        fun onPanStop(resultBonusIndex: Int)
        fun onPanStopWithErr()
        fun onBonusBackgroundAnimEnter()
        fun onBonusBackgroundAnimExit()
    }
}