package mm.chenme.lib.commutillibdemo.complex_demo.interview.base.pass_value4service

import android.app.IntentService
import android.content.Intent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mm.chenme.lib.commutillib.utils.loge

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/8/21
 * Email：ibelieve1210@163.com
 */

class SelfIntentService : IntentService("SelfIntentService") {

    override fun onHandleIntent(intent: Intent?) { // 此处处理耗时操作
        Thread.sleep(5000)
        loge(intent?.getStringExtra("data") ?: "未获取到数据", "---CME---SelfIntentService.onHandleIntent()")
    }

    override fun onCreate() {
        super.onCreate()
        loge("创建", "---CME---SelfIntentService.onCreate()")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        loge("接收到命令", "---CME---SelfIntentService.onStartCommand()")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        loge("启动", "---CME---SelfIntentService.onStart()")
    }

    override fun onDestroy() {
        super.onDestroy()
        loge("销毁", "---CME---SelfIntentService.onDestroy()")
    }
}

/*





 */