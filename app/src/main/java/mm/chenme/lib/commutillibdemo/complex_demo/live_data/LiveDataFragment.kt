package mm.chenme.lib.commutillibdemo.complex_demo.live_data

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.View
import android.widget.SeekBar
import androidx.lifecycle.Observer
import com.opensource.svgaplayer.SVGADrawable
import com.opensource.svgaplayer.SVGAParser
import com.opensource.svgaplayer.SVGAParser.ParseCompletion
import com.opensource.svgaplayer.SVGAVideoEntity
import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.frg_live_data.*
import mm.chenme.lib.commutillib.BaseFragment
import mm.chenme.lib.commutillib.utils.bindViewModel
import mm.chenme.lib.commutillib.utils.setVisible
import mm.chenme.lib.commutillibdemo.R


/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/9/10
 * Email：ibelieve1210@163.com
 */
class LiveDataFragment(override val layoutResId: Int = R.layout.frg_live_data) : BaseFragment() {

    private lateinit var mSeekBarVM: SeekBarVM

    override fun initData() {
        super.initData()
        mSeekBarVM = requireActivity().bindViewModel()
        SVGAParser.shareParser().init(requireActivity())
    }

    override fun initView() {
        super.initView()
        loadSVGAAnim()
    }

    override fun initListener() {
        super.initListener()

        sb_fragment.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                mSeekBarVM.seekBarValue.value = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        mSeekBarVM.seekBarValue.observe(this, Observer {
            sb_fragment.progress = it
        })

        // test ConstraintLayout2.x Flow
        flow.onClick {
            tv2.setVisible(false)
            tv3.setVisible(false)
        }

        cl_loginByPwd.onClick {
            toggleStatus(cl_loginByVerification, cl_loginByPwd)
        }
        cl_loginByVerification.onClick {
            toggleStatus(cl_loginByPwd, cl_loginByVerification)
        }
    }

    private fun loadSVGAAnim() {
        val parser = SVGAParser(requireActivity())
//        parser.decodeFromURL(URL("https://github.com/yyued/SVGA-Samples/blob/master/posche.svga?raw=true"), object : ParseCompletion {
//            override fun onComplete(videoItem: SVGAVideoEntity) {
//                imageView.setImageDrawable(SVGADrawable(videoItem))
//                imageView.startAnimation()
//            }
//
//            override fun onError() {}
//        })

        parser.decodeFromAssets("rose.svga", object : ParseCompletion {
            override fun onComplete(videoItem: SVGAVideoEntity) {
                imageView.setImageDrawable(SVGADrawable(videoItem))
                imageView.startAnimation()
            }

            override fun onError() {}
        })
    }

    private fun toggleStatus(open: View, close: View) {
        val anim2 = ObjectAnimator.ofFloat(open, "rotationX", 90f, 180f)
        anim2.duration = 150
        anim2.start()

        val anim = ObjectAnimator.ofFloat(close, "rotationX", 0f, 90f)
        anim.duration = 150
        anim.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                close.setVisible(false)
                open.setVisible(true)
                anim2.start()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }
        })
        anim.start()
    }
}