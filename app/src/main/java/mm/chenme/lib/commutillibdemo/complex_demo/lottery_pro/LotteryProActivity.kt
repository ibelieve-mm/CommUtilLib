package mm.chenme.lib.commutillibdemo.complex_demo.lottery_pro

import android.animation.ObjectAnimator
import android.view.animation.DecelerateInterpolator
import com.qmuiteam.qmui.kotlin.dip
import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.act_lottery_pro.*
import mm.chenme.lib.commutillib.BaseActivity
import mm.chenme.lib.commutillibdemo.R

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/9/9
 * Email：ibelieve1210@163.com
 */
class LotteryProActivity(override val layoutResId: Int = R.layout.act_lottery_pro) : BaseActivity() {

    private lateinit var mRotate: ObjectAnimator

    override fun initView() {
        super.initView()
        topbar.setTitle("自适应文案 Lottery")
        topbar.addLeftBackImageButton().onClick { closePage() }

        mRotate = ObjectAnimator.ofFloat(fl_bg, "rotation", 0f, 360f * 9.9f)
        mRotate.interpolator = DecelerateInterpolator()
        mRotate.duration = 3600

        var angle = 0f
        val tvs = arrayListOf(tv0, tv1, tv2, tv3, tv4, tv5, tv6, tv7)
        val lls = arrayListOf(ll0, ll1, ll2, ll3, ll4, ll5, ll6, ll7)
        val texts = arrayListOf("180 coins to lottery good good study", "180 coins to lottery", "180 coins to lottery", "123", "123", "123", "123", "123")
        lls.forEachIndexed { index, it ->
            tvs[index].text = texts[index]
            if (index > 0) {
                it.pivotX = dip(40).toFloat()
                it.pivotY = dip(140).toFloat()
                it.cameraDistance = 100f
                it.rotation = angle
            }
            angle += 45f
        }
    }

    override fun initListener() {
        super.initListener()
        srb_startLottery.onClick { mRotate.start() }
    }
}