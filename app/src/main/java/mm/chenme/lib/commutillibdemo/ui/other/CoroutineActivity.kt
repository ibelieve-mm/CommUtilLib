package mm.chenme.lib.commutillibdemo.ui.other

import android.os.CountDownTimer
import androidx.core.content.edit
import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.act_coroutine.*
import kotlinx.android.synthetic.main.act_glide_scale_type.*
import kotlinx.android.synthetic.main.act_glide_scale_type.topbar
import kotlinx.android.synthetic.main.act_shared_preferences.*
import kotlinx.coroutines.*
import mm.chenme.lib.commutillib.BaseActivity
import mm.chenme.lib.commutillib.utils.*
import mm.chenme.lib.commutillibdemo.R


/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/6/3
 * Email：ibelieve1210@163.com
 */
class CoroutineActivity(override val layoutResId: Int = R.layout.act_coroutine) : BaseActivity() {

    private val viewModelJob = Job()    //用来取消协程
    override fun initView() {
        topbar.setTitle("协程测试")
        topbar.addLeftBackImageButton().onClick { closePage() }
    }

    override fun initListener() {
        tv_start.onClick {
            tv_output.text = ""

            val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob) //初始化 CoroutineScope 指定协程的运行所在线程传入 Job 方便后面取消协程
            uiScope.launch { //启动一个协程
                updateUI() //suspend函数运行在协程内或者suspend另外一个函数内
            }

            object : CountDownTimer(10000L, 500) {
                override fun onFinish() {
                    updateText("倒计时结束")
                }

                override fun onTick(millisUntilFinished: Long) {
                    updateText("当前剩余时间：$millisUntilFinished")
                }

            }.start()

        }

        tv_cancel.onClick {
            viewModelJob.cancel()
            updateText("协程已取消")
        }
    }

    private suspend fun updateUI() {
        delay(10000L) //delay是一个 suspend 函数
        updateText("协程结束！")
    }

    private fun updateText(text:String){
        tv_output.text = "$text\n${tv_output.text}"
    }
}

