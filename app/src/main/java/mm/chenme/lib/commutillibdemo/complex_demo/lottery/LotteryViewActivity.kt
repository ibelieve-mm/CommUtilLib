package mm.chenme.lib.commutillibdemo.complex_demo.lottery

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.animation.*
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.animation.addListener
import com.qmuiteam.qmui.kotlin.dip
import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.act_lottery_view.*
import mm.chenme.lib.commutillib.BaseActivity
import mm.chenme.lib.commutillib.utils.catchInt
import mm.chenme.lib.commutillib.utils.screenWidthPx
import mm.chenme.lib.commutillib.utils.setVisible
import mm.chenme.lib.commutillib.utils.st
import mm.chenme.lib.commutillibdemo.R


/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/8/20
 * Email：ibelieve1210@163.com
 */
class LotteryViewActivity(override val layoutResId: Int = R.layout.act_lottery_view) : BaseActivity() {

    private val mBonusSize = 9
    private var resultIndex = 0

    private val mIconList = mutableListOf(R.mipmap.off, R.mipmap.gems, R.mipmap.points, R.mipmap.prime)

    override fun initView() {
        super.initView()
        initTopBar(topbar,"Lottery")

        val bonusList = mutableListOf<BonusBean>()
        (0..mBonusSize).forEach { i ->
            bonusList.add(BonusBean("1$i Days", mIconList[i % 4]))
        }
        val lp = lv.layoutParams
        lp.height = screenWidthPx - dip(10) * 2
        lv.layoutParams = lp

        lv.setBonusList(bonusList)
        lv.onLotteryViewEvent(object : LotteryView.OnLotteryViewEvent {
            override fun onPointerClick(): Boolean {
                lv.showFreeTag(false)
                resultIndex = et_result.catchInt()
                if (resultIndex < 0 || resultIndex > mBonusSize) {
                    st("请输入 0 - $mBonusSize ")
                    return false
                }
//                lv.setResultBonusIndex(result)
                btn_fill.setVisible(true)
                btn_err.setVisible(true)
                return true
            }

            override fun onPanStop(resultBonusIndex: Int) {
                st("!!恭喜获得奖品$resultBonusIndex")
            }

            override fun onPanStopWithErr() {
                st("!!!!!!!!!!!!!出错啦")
            }

            override fun onBonusBackgroundAnimEnter() {
                resultIconEnter()
            }

            override fun onBonusBackgroundAnimExit() {
                lv.showFreeTag()
                resultIconExit(iv_pointsIcon)
            }
        })

        btn_fill.onClick {
            btn_fill.setVisible(false)
            btn_err.setVisible(false)
            lv.lotteryStartWithOK(resultIndex)
        }

        btn_err.onClick {
            btn_fill.setVisible(false)
            btn_err.setVisible(false)
            lv.lotteryStartWithErr()
        }
    }

    fun resultIconEnter() {
        tv_resultText.text = "x 123"
        iv_resultIcon.setImageResource(R.mipmap.points_160)
        tv_resultText.setVisible(true)
        iv_resultIcon.setVisible(true)
        val scaleX = ObjectAnimator.ofFloat(iv_resultIcon, "scaleX", 0f, 1f)
        val scaleY = ObjectAnimator.ofFloat(iv_resultIcon, "scaleY", 0f, 1f)
        val scaleX1 = ObjectAnimator.ofFloat(tv_resultText, "scaleX", 0f, 1f)
        val scaleY1 = ObjectAnimator.ofFloat(tv_resultText, "scaleY", 0f, 1f)
        scaleX.interpolator = OvershootInterpolator()
        scaleY.interpolator = OvershootInterpolator()

        interpolatorAnim(OvershootInterpolator(), scaleX, scaleY, scaleX1, scaleY1)
        val set = AnimatorSet()
        set.play(scaleX).with(scaleY).with(scaleX1).with(scaleY1)
        set.duration = 300
        set.start()
    }

    fun resultIconExit(to: AppCompatImageView) {

        val scaleX1 = ObjectAnimator.ofFloat(tv_resultText, "scaleX", 1f, 0f)
        val scaleY1 = ObjectAnimator.ofFloat(tv_resultText, "scaleY", 1f, 0f)
        val set1 = AnimatorSet()
        set1.play(scaleX1).with(scaleY1)
        set1.duration = 300
        set1.start()
        set1.addListener(onEnd = { _ -> tv_resultText.setVisible(false) })


        val scale = ScaleAnimation(1f, .25f, 1f, .25f)

//        val globalRect = Rect()
//        iv_resultIcon.getGlobalVisibleRect(globalRect)
//        val globalRectTarget = Rect()
//        iv_pointsIcon.getGlobalVisibleRect(globalRect)
//        val x1 = globalRectTarget.left-globalRect.left
//        val y1 = globalRectTarget.top-globalRect.top
        val x1 = to.left - iv_resultIcon.left
        val y1 = to.top - iv_resultIcon.top
        val translate = TranslateAnimation(0f, x1.toFloat(), 0f, y1.toFloat())

        val set = AnimationSet(true)
        set.addAnimation(scale)
        set.addAnimation(translate)
        set.duration = 600
        set.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                iv_resultIcon.setVisible(false)
            }

            override fun onAnimationStart(animation: Animation?) {
            }
        })

        iv_resultIcon.startAnimation(set)
    }

    private fun interpolatorAnim(interpolator: BaseInterpolator, vararg objAnims: ObjectAnimator) {
        objAnims.forEach { it.interpolator = interpolator }
    }
}