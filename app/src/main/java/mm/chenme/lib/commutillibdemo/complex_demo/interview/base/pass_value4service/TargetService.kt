package mm.chenme.lib.commutillibdemo.complex_demo.interview.base.pass_value4service

import android.app.Service
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import android.os.CountDownTimer
import android.os.IBinder
import mm.chenme.lib.commutillib.utils.loge

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/8/18
 * Email：ibelieve1210@163.com
 */
class TargetService : Service() {

    private var mCallback: Callback? = null

    override fun onBind(intent: Intent): IBinder {
        loge("绑定", "---CME---TargetService.onBind()")

        object : CountDownTimer(10_000L, 1_000) {
            override fun onFinish() {
                mCallback?.onDataChange("倒计时结束了！")
            }

            override fun onTick(millisUntilFinished: Long) {
                mCallback?.onDataChange("当前剩余时间：$millisUntilFinished")
            }

        }.start()

        return MyBinder()
    }

    override fun unbindService(conn: ServiceConnection) {
        loge("解绑 Service", "---CME---TargetService.unbindService()")
        super.unbindService(conn)
    }

    fun setCallback(callback: Callback) {
        this.mCallback = callback
    }

    inner class MyBinder : Binder() {
        fun getMyService(): TargetService {
            return this@TargetService
        }
    }

    interface Callback {
        fun onDataChange(data: String)
    }
}