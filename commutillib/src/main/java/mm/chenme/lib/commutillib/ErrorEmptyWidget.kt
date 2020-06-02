package mm.chenme.lib.commutillib

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
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

    private lateinit var mContentView: View


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

    fun loadContentView(view: View) {
        mContentView = view
    }

    fun showContent() {
        if (mShowStatus == ShowStatus_Content) {
            return
        }
        mShowStatus = ShowStatus_Content
        mContentView.setVisible(true)
        this.setVisible(false)
    }

    fun showError(retry: () -> Unit) {
        if (mShowStatus == ShowStatus_Error) {
            return
        }
        mShowStatus = ShowStatus_Error
        mContentView.setVisible(false)
        this.setVisible(true)
        srb_retry.onClick { retry() }
    }
}