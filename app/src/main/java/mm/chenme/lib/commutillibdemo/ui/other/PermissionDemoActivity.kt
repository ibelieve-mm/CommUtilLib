package mm.chenme.lib.commutillibdemo.ui.other

import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.ScreenUtils
import kotlinx.android.synthetic.main.act_permission.*
import mm.chenme.lib.commutillib.BaseActivity
import mm.chenme.lib.commutillib.utils.loge
import mm.chenme.lib.commutillibdemo.R

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：1/28/21
 * Email：ibelieve1210@163.com
 */
class PermissionDemoActivity(override val layoutResId: Int = R.layout.act_permission) : BaseActivity() {

    override fun initView() {
        super.initView()
        initTopBar(topbar, "Permission Demo")
        initPermission()
    }

    private fun initPermission() {
        val all = PermissionUtils.isGranted(PermissionConstants.CAMERA, PermissionConstants.MICROPHONE)
        val camera = PermissionUtils.isGranted(PermissionConstants.CAMERA, PermissionConstants.MICROPHONE)
        val mic = PermissionUtils.isGranted(PermissionConstants.CAMERA, PermissionConstants.MICROPHONE)

        loge("all：$all, camera：$camera, mic：$mic", "---CME---PermissionDemoActivity.initPermission()")

        PermissionUtils.permission(PermissionConstants.CAMERA, PermissionConstants.MICROPHONE) // 设置请求权限
            .rationale { activity, shouldRequest -> // 设置拒绝权限后再次请求的回调接口
                loge("权限被拒绝", "---CME---PermissionDemoActivity.initPermission()")
                shouldRequest.again(true)
            }
            .callback(object : PermissionUtils.FullCallback { // 设置回调
                override fun onGranted(granted: MutableList<String>) {
                    loge("通过", "---CME---PermissionDemoActivity.onGranted()")
                    granted.forEach {
                        loge("++ $it", "---CME---PermissionDemoActivity.onGranted()")
                    }
                }

                override fun onDenied(deniedForever: MutableList<String>, denied: MutableList<String>) {
                    loge("被拒绝", "---CME---PermissionDemoActivity.onDenied()")
                    deniedForever.forEach {
                        loge("永久被拒绝：$it", "---CME---PermissionDemoActivity.onDenied()")
                    }
                    denied.forEach {
                        loge("本次被拒绝：$it", "---CME---PermissionDemoActivity.onDenied()")
                    }
                }
            })
//            .theme { activity -> ScreenUtils.setFullScreen(activity) }
            .request()
    }


}