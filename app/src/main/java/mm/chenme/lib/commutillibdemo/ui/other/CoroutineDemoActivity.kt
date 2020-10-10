package mm.chenme.lib.commutillibdemo.ui.other

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mm.chenme.lib.commutillib.BaseActivity
import mm.chenme.lib.commutillib.utils.loge
import mm.chenme.lib.commutillibdemo.R
import kotlin.concurrent.thread

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/9/30
 * Email：ibelieve1210@163.com
 */
class CoroutineDemoActivity(override val layoutResId: Int = R.layout.act_main) : BaseActivity() {

    /**
     * 1. 使用线程的写法
     */
    /*override fun initView() {
        super.initView()
        thread {
            ioCode1()
            runOnUiThread {
                uiCode1()
                thread {
                    ioCode2()
                    runOnUiThread {
                        uiCode2()
                        thread {
                            ioCode3()
                            runOnUiThread {
                                uiCode3()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun ioCode1() {
        loge("Thread : ${Thread.currentThread().name}", "---CME---CoroutineDemoActivity.ioCode1()")
    }

    private fun uiCode1() {
        loge("Thread : ${Thread.currentThread().name}", "---CME---CoroutineDemoActivity.uiCode1()")
    }

    private fun ioCode2() {
        loge("Thread : ${Thread.currentThread().name}", "---CME---CoroutineDemoActivity.ioCode2()")
    }

    private fun uiCode2() {
        loge("Thread : ${Thread.currentThread().name}", "---CME---CoroutineDemoActivity.uiCode2()")
    }

    private fun ioCode3() {
        loge("Thread : ${Thread.currentThread().name}", "---CME---CoroutineDemoActivity.ioCode3()")
    }

    private fun uiCode3() {
        loge("Thread : ${Thread.currentThread().name}", "---CME---CoroutineDemoActivity.uiCode3()")
    }*/


    /**
     * 2. 使用协程优化
     */
    override fun initView() {
        super.initView()
        GlobalScope.launch(Dispatchers.Main) {
            ioCode1()
            uiCode1()
            ioCode2()
            uiCode2()
            ioCode3()
            uiCode3()
        }
    }

    private suspend fun ioCode1() = withContext(Dispatchers.IO) {
        loge("Thread : ${Thread.currentThread().name}", "---CME---CoroutineDemoActivity.ioCode1()")
    }

    private fun uiCode1() {
        loge("Thread : ${Thread.currentThread().name}", "---CME---CoroutineDemoActivity.uiCode1()")
    }

    private suspend fun ioCode2() = withContext(Dispatchers.IO) {
        loge("Thread : ${Thread.currentThread().name}", "---CME---CoroutineDemoActivity.ioCode2()")
    }

    private fun uiCode2() {
        loge("Thread : ${Thread.currentThread().name}", "---CME---CoroutineDemoActivity.uiCode2()")
    }

    private suspend fun ioCode3() = withContext(Dispatchers.IO) {
        loge("Thread : ${Thread.currentThread().name}", "---CME---CoroutineDemoActivity.ioCode3()")
    }

    private fun uiCode3() {
        loge("Thread : ${Thread.currentThread().name}", "---CME---CoroutineDemoActivity.uiCode3()")
    }
}
