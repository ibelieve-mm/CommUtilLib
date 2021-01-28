package mm.chenme.lib.commutillibdemo.complex_demo.interview.base.pass_value4service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.UtilsTransActivity
import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.act_service.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mm.chenme.lib.commutillib.utils.loge
import mm.chenme.lib.commutillibdemo.R

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/8/18
 * Email：ibelieve1210@163.com
 */
class ServiceActivity : AppCompatActivity(), ServiceConnection {

    private lateinit var mIntent: Intent

    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            tv_result.text = "${tv_result.text}\n${msg.data.getString("data")}"
        }
    }


    fun getPermission() {
        Log.i("SplashActivity", "getPermission()")
        PermissionUtils.permission(PermissionConstants.CAMERA)
            .rationale(object : PermissionUtils.OnRationaleListener {
                override fun rationale(activity: UtilsTransActivity, shouldRequest: PermissionUtils.OnRationaleListener.ShouldRequest) {
                    Log.i("SplashActivity", "getPermission  rationale()")
                    shouldRequest.again(true)
                }
            })
            .callback(object : PermissionUtils.FullCallback {
                override fun onGranted(granted: MutableList<String>) {
                    Log.i("SplashActivity", "onGranted()")
                }

                override fun onDenied(deniedForever: MutableList<String>, denied: MutableList<String>) {
                    Log.i("SplashActivity", "onDenied()")
                }
            }).request()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_service)

        getPermission()

        mIntent = Intent(this, TargetService::class.java)

        srb_startService.onClick {
            bindService(mIntent, this, Context.BIND_AUTO_CREATE)
        }

        srb_stopService.onClick {
            unbindService(this)
        }

        val serviceIntent = Intent(this, SelfIntentService::class.java)
        serviceIntent.putExtra("data", "I'm from Activity!")
        startService(serviceIntent)
        loge("第一次启动", "---CME---LotteryViewActivity.initView()")
        CoroutineScope(Dispatchers.Default).launch {
            delay(3000)
            startService(serviceIntent)
            loge("第二次启动", "---CME---LotteryViewActivity.initView()")
            delay(3000)
            startService(serviceIntent)
            loge("第三次启动", "---CME---LotteryViewActivity.initView()")
        }
    }

    override fun onServiceDisconnected(name: ComponentName?) {}

    override fun onServiceConnected(name: ComponentName?, service: IBinder) {
        val myBinder = service as TargetService.MyBinder
        myBinder.getMyService().setCallback(object : TargetService.Callback {
            override fun onDataChange(data: String) {
                val msg = Message()
                val bundle = Bundle()
                bundle.putString("data", data)
                msg.data = bundle
                mHandler.sendMessage(msg)
            }
        })
    }
}