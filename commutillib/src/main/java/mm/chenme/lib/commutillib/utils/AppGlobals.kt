package mm.chenme.lib.commutillib.utils

import android.app.Application

/**
 * Descriptions：全局的工具类
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/5/20
 * Email：ibelieve1210@163.com
 */
object AppGlobals {
    private var mApplication: Application? = null

    /**
     * 获取 Application 对象
     * 2020/5/20，ChenME
     * @return application 实例
     */
    fun application(): Application {
        if (null == mApplication) {
            val method = Class.forName("android.app.ActivityThread").getDeclaredMethod("currentApplication")
            mApplication = method.invoke(null) as Application
        }
        return mApplication!!
    }
}