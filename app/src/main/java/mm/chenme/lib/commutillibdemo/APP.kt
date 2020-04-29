package mm.chenme.lib.commutillibdemo

import android.app.Application
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020-04-29
 * Email：ibelieve1210@163.com
 */
class APP : Application() {
    override fun onCreate() {
        super.onCreate()
        QMUISwipeBackActivityManager.init(this)
    }
}