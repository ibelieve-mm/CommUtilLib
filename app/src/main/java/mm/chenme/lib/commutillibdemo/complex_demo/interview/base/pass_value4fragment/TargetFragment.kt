package ly.omegle.android.app.mvp.videocall

import android.content.Context
import androidx.fragment.app.Fragment

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/8/17
 * Email：ibelieve1210@163.com
 */
class TargetFragment : Fragment() {

    private var mFragmentCallback: FragmentCallback? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // 回调传值：③将 Activity 实例转换成该接口并赋值
        if (context is FragmentCallback) {
            mFragmentCallback = context
        }
    }

    override fun onDetach() {
        super.onDetach()

        // 回调传值：④将第③步中的 Activity 对象释放掉，避免引起内存泄漏
        mFragmentCallback = null
    }

    private fun doSomething() {
        mFragmentCallback?.process("要传递的数据")
    }

    // 回调传值：①创建回调接口
    interface FragmentCallback {
        fun process(arg: String)
    }
}


