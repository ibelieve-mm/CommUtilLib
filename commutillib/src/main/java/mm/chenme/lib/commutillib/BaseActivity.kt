package mm.chenme.lib.commutillib

import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.qmuiteam.qmui.arch.QMUIFragmentActivity
import com.qmuiteam.qmui.kotlin.onClick
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.qmuiteam.qmui.widget.QMUITopBar
import mm.chenme.lib.commutillib.utils.color
import mm.chenme.lib.commutillib.utils.loge
import mm.chenme.lib.commutillib.utils.string

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020-04-30
 * Email：ibelieve1210@163.com
 */
abstract class BaseActivity : QMUIFragmentActivity() {

    abstract val layoutResId: Int // 页面布局资源ID
    open val isStatusBarLightMode: Boolean = true // 是否显示浅色状态栏模式(即：状态栏背景：浅色，字体：黑色)

    open fun initData() {} // 初始化数据
    open fun initView() {} // 初始化View
    open fun initListener() {} // 初始化Listener
    open fun loadData() {} // 请求数据

    fun initTopBar(
        topBar: QMUITopBar,
        @StringRes titleRes: Int, @ColorRes colorTitleRes: Int? = null,
        subtitle: String? = null, @ColorRes colorSubtitleRes: Int? = null,
        isShowBackBtn: Boolean = true
    ) {
        initTopBar(topBar, string(titleRes), if (null != colorTitleRes) color(colorTitleRes) else null, subtitle, if (null != colorSubtitleRes) color(colorSubtitleRes) else null, isShowBackBtn)
    }

    fun initTopBar(
        topBar: QMUITopBar,
        title: String, colorTitle: Int? = null,
        subtitle: String? = null, colorSubtitle: Int? = null,
        isShowBackBtn: Boolean = true
    ) {
        val fvTitle = topBar.setTitle(title)
        colorTitle?.let { fvTitle.setTextColor(it) }

        if (isShowBackBtn) {
            topBar.addLeftBackImageButton().onClick { closePage() }
        }

        subtitle?.let {
            val fvSubtitle = topBar.setSubTitle(subtitle)
            colorSubtitle?.let { color ->
                fvSubtitle.setTextColor(color)
            }
        }
    }

    fun dataEmpty() {
        loge("-------- BaseActivity ------------> 数据为空")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)

        initData()

        QMUIStatusBarHelper.translucent(this) // 设置沉浸模式
        if (isStatusBarLightMode) { // 设置状态栏黑色字体
            QMUIStatusBarHelper.setStatusBarLightMode(this)
        } else { // 设置状态栏白色字体
            QMUIStatusBarHelper.setStatusBarDarkMode(this)
        }

        initView()
        initListener()
        loadData()
    }

    fun closePage() {
        onBackPressed()
    }
}