package mm.chenme.lib.commutillibdemo.ui.other

import androidx.core.content.edit
import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.act_shared_preferences.*
import kotlinx.android.synthetic.main.act_shared_preferences.topbar
import kotlinx.android.synthetic.main.act_tab_layout_divider.*
import mm.chenme.lib.commutillib.BaseActivity
import mm.chenme.lib.commutillib.utils.getSP
import mm.chenme.lib.commutillib.utils.ltoast
import mm.chenme.lib.commutillib.utils.string
import mm.chenme.lib.commutillibdemo.R


/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/6/3
 * Email：ibelieve1210@163.com
 */
class SharedPreferencesActivity(override val layoutResId: Int = R.layout.act_shared_preferences) : BaseActivity() {


    override fun initView() {
        initTopBar(topbar,"SharedPreferences 测试")
    }

    override fun initListener() {
        tv_write.onClick {
            getSP().edit { putString("input", et_input.text.toString()) }
        }
        tv_read.onClick { ltoast(getSP().string("input")) }

        tv_del.onClick { getSP().edit { putString("input", "") } }
    }
}

