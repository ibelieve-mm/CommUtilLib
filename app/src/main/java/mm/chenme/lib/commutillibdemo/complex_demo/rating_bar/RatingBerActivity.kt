package mm.chenme.lib.commutillibdemo.complex_demo.rating_bar

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.graphics.Color
import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.act_rating_bar.*
import mm.chenme.lib.commutillib.BaseActivity
import mm.chenme.lib.commutillib.utils.st
import mm.chenme.lib.commutillibdemo.R

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/9/17
 * Email：ibelieve1210@163.com
 */
class RatingBerActivity : BaseActivity() {
    override val layoutResId: Int
        get() = R.layout.act_rating_bar


    override fun initListener() {
        super.initListener()
        iv_star.onClick {
            val anim = ObjectAnimator.ofInt(iv_star, "textColor", Color.GREEN, Color.RED)
            anim.setEvaluator(ArgbEvaluator())
            anim.duration = 3000
            anim.addUpdateListener {
                iv_star.setColorFilter((it.animatedValue as Int))
            }
            anim.start()

            st(simpleRatingBar.rating.toString())
        }

    }

}