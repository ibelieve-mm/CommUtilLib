package mm.chenme.lib.commutillibdemo.complex_demo.live_data

import android.widget.SeekBar
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.lifecycle.Observer
import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.act_live_data.*
import mm.chenme.lib.commutillib.BaseActivity
import mm.chenme.lib.commutillib.utils.bindViewModel
import mm.chenme.lib.commutillibdemo.R

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/9/10
 * Email：ibelieve1210@163.com
 */

class LiveDataActivity(override val layoutResId: Int = R.layout.act_live_data) : BaseActivity() {

    private lateinit var mSeekBarVM: SeekBarVM

    override fun initData() {
        super.initData()
        mSeekBarVM = bindViewModel()
    }

    override fun initView() {
        super.initView()
        initTopBar(topbar,"LiveData Demo")
    }

    override fun initListener() {
        super.initListener()
        mSeekBarVM.seekBarValue.observe(this, Observer {
            sb_activity.progress = it
        })

        sb_activity.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                mSeekBarVM.seekBarValue.value = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        srb_openLiveData.onClick {
            val spring= SpringForce(0f)
                .setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY)
                .setStiffness(SpringForce.STIFFNESS_LOW)
            val anim = SpringAnimation(view,DynamicAnimation.X,0f).setSpring(spring).setStartVelocity(5000f)
            anim.start()
        }


    }
}