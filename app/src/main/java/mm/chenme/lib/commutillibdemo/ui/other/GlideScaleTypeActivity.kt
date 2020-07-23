package mm.chenme.lib.commutillibdemo.ui.other

import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.act_glide_scale_type.*
import kotlinx.android.synthetic.main.act_glide_scale_type.topbar
import kotlinx.android.synthetic.main.act_tab_layout_divider.*
import mm.chenme.lib.commutillib.BaseActivity
import mm.chenme.lib.commutillib.utils.show
import mm.chenme.lib.commutillibdemo.R


/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/6/3
 * Email：ibelieve1210@163.com
 */
class GlideScaleTypeActivity(override val layoutResId: Int = R.layout.act_glide_scale_type) : BaseActivity() {


    private val heightImgUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1591275826580&di=8e38ccf1802a62d89afe052734b7c354&imgtype=0&src=http%3A%2F%2Fcdn.duitang.com%2Fuploads%2Fitem%2F201305%2F12%2F20130512095153_WSu5Q.jpeg"
    private val widthImgUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1591276322766&di=4fd14347e58ba03856a95229e39693e5&imgtype=0&src=http%3A%2F%2Fattimg.dospy.com%2Fimg%2Fday_130714%2F20130714_ec6461694efe14f339fe6JpXmWkl4kBK.jpg"

    override fun initView() {
        initTopBar(topbar,"Glide 中图片缩放模式测试")
    }

    override fun initListener() {
        tv_showHeight.onClick { loadImg(heightImgUrl) }
        tv_showWidth.onClick { loadImg(widthImgUrl) }

        tv_showHeight2.onClick { loadImg(heightImgUrl, 30f) }
        tv_showWidth2.onClick { loadImg(widthImgUrl, 30f) }
    }

    private fun loadImg(imgUrl: String, radius: Float = 0f) {
//        iv1.circle(imgUrl)
//        iv2.circle(imgUrl)
//        iv3.circle(imgUrl)
//        iv4.circle(imgUrl)
//        iv5.circle(imgUrl)
//        iv6.circle(imgUrl)
//        iv7.circle(imgUrl)
//
//        iv1.show(imgUrl, radiusDp = radius)
//        iv2.show(imgUrl, radiusDp = radius)
//        iv3.show(imgUrl, radiusDp = radius)
//        iv4.show(imgUrl, radiusDp = radius)
//        iv5.show(imgUrl, radiusDp = radius)
//        iv6.show(imgUrl, radiusDp = radius)
//        iv7.show(imgUrl, radiusDp = radius)


        iv1.show(imgUrl, radiusDp = radius, isUseCenterCrop = false)
        iv2.show(imgUrl, radiusDp = radius, isUseCenterCrop = false)
        iv3.show(imgUrl, radiusDp = radius, isUseCenterCrop = false)
        iv4.show(imgUrl, radiusDp = radius, isUseCenterCrop = false)
        iv5.show(imgUrl, radiusDp = radius, isUseCenterCrop = false)
        iv6.show(imgUrl, radiusDp = radius, isUseCenterCrop = false)
        iv7.show(imgUrl, radiusDp = radius, isUseCenterCrop = false)
    }
}

