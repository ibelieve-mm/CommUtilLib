package mm.chenme.lib.commutillibdemo.ui.other

import kotlinx.android.synthetic.main.act_shapeable_image_view.*
import mm.chenme.lib.commutillib.BaseActivity
import mm.chenme.lib.commutillibdemo.R

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2021/1/6
 * Email：ibelieve1210@163.com
 */
class ShapeableImageViewActivity(override val layoutResId: Int = R.layout.act_shapeable_image_view) : BaseActivity() {

    override fun initView() {
        super.initView()
        initTopBar(topbar, "ShapeableImageView 测试")

    }
}