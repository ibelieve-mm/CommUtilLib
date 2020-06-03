package mm.chenme.lib.commutillib

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.OnCompositionLoadedListener
import com.qmuiteam.qmui.kotlin.dip
import com.qmuiteam.qmui.kotlin.onClick
import kotlinx.android.synthetic.main.ui_error_empty.view.*
import mm.chenme.lib.commutillib.utils.setVisible


/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/6/2
 * Email：ibelieve1210@163.com
 */
class ErrorEmptyWidget @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var mContentView: View // 内容布局
    private var mReSizeDp = 100 // 动画画布的大小

    companion object {
        private const val ShowStatus_Content = 1
        private const val ShowStatus_Loading = 2
        private const val ShowStatus_Empty = 3
        private const val ShowStatus_Error = 4

    }

    private var mShowStatus = ShowStatus_Content

    init {
        initView()
    }

    private fun initView() {
        View.inflate(context, R.layout.ui_error_empty, this)
        this.setVisible(false)
    }

    private fun showErrorEmptyView(tipsTitle: String, tipsSubTitle: String, animPath: String, reSizeDp: Int = 100) {
        if (mReSizeDp != reSizeDp) {
            mReSizeDp = reSizeDp
            val lp = lav_loading.layoutParams
            lp.width = dip(mReSizeDp)
            lp.height = dip(mReSizeDp)
            lav_loading.layoutParams = lp
        }
        tv_tipsTitle.text = tipsTitle
        if (tipsSubTitle.isNotEmpty()) {
            tv_tipsSubTitle.text = tipsSubTitle
            tv_tipsSubTitle.setVisible(true)
        } else {
            tv_tipsSubTitle.setVisible(false)
        }
        lav_loading.setAnimation(animPath)
        lav_loading.playAnimation()
        mContentView.setVisible(false)
        this.setVisible(true)
    }

    fun loadContentView(contentView: View) {
        mContentView = contentView
    }

//    fun loadContentView(contentView: View, @ColorInt retryBtnBgColor: Int? = null) {
//        mContentView = contentView
//        retryBtnBgColor?.apply { srb_retry.setBackgroundColor(retryBtnBgColor)
//            srb_retry.invalidate()}
//
//    }

    /**
     * 显示内容视图
     * 2020/6/3，ChenME
     */
    fun showContent() {
        if (mShowStatus == ShowStatus_Content) {
            return
        }
        lav_loading.cancelAnimation()
        mShowStatus = ShowStatus_Content
        mContentView.setVisible(true)
        this.setVisible(false)
    }

    /**
     * 显示 loading
     * 2020/6/3，ChenME
     * @param  tipsTitle    : 第一行提示
     * @param  tipsSubTitle : 第二行提示
     */
    fun showLoading(tipsTitle: String = "别着急唷~正在努力加载中~", tipsSubTitle: String = "") {
        if (mShowStatus == ShowStatus_Loading) {
            return
        }
        mShowStatus = ShowStatus_Loading
        srb_retry.setVisible(false)
        showErrorEmptyView(tipsTitle, tipsSubTitle, "loading_point_circle.json", 70)
    }

    /**
     * 数据为空
     * 2020/6/3，ChenME
     * @param  tipsTitle    : 第一行提示
     * @param  tipsSubTitle : 第二行提示
     */
    fun showEmpty(tipsTitle: String = "哎唷~还没有数据呢", tipsSubTitle: String = "") {
        if (mShowStatus == ShowStatus_Empty) {
            return
        }
        mShowStatus = ShowStatus_Empty
        srb_retry.setVisible(false)
        showErrorEmptyView(tipsTitle, tipsSubTitle, "empty_box.json")
    }

    /**
     * 加载错误
     * 2020/6/3，ChenME
     * @param  tipsTitle    : 第一行提示
     * @param  tipsSubTitle : 第二行提示
     * @param  retryText    : 按钮显示的文字
     * @param  retry        : 点击按钮的响应事件
     */
    fun showError(tipsTitle: String = "哎唷~加载出错啦~客官稍等再试", tipsSubTitle: String = "点击按钮重新加载唷~~", retryText: String? = null, retry: () -> Unit) {
        if (mShowStatus == ShowStatus_Error) {
            return
        }
        retryText?.apply { srb_retry.text = this }
        mShowStatus = ShowStatus_Error
        srb_retry.setVisible(true)
        showErrorEmptyView(tipsTitle, tipsSubTitle, "loading_triangle.json", 180)
        srb_retry.onClick { retry() }
    }
}