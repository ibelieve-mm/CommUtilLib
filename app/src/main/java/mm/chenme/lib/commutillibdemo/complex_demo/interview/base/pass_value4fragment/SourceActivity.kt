package ly.omegle.android.app.mvp.videocall

import androidx.appcompat.app.AppCompatActivity
import mm.chenme.lib.commutillibdemo.APP

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/8/17
 * Email：ibelieve1210@163.com
 */
class SourceActivity : AppCompatActivity(), TargetFragment.FragmentCallback { // 回调传值：②实现回调接口
    override fun process(arg: String) {
        // 回调传值：⑤拿到 Fragment 传递过来的数据
        TODO("Not yet implemented")

        APP.instance()
    }
}