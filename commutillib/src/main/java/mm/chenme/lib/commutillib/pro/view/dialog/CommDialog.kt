package mm.chenme.lib.commutillib.pro.view.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import com.qmuiteam.qmui.kotlin.onClick
import mm.chenme.lib.commutillib.R
import mm.chenme.lib.commutillib.utils.setVisible

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/6/14
 * Email：ibelieve1210@163.com
 */


class CommDialog(
    private val content: String,
    private val btnList: MutableList<String>,
    private val title: String? = null,
    private val isOutSideCancel: Boolean = true,
    private val isCancel: Boolean = true,
    private val onClick: (dialog: Dialog?, index: Int) -> Boolean
) : BaseDialog() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.setCanceledOnTouchOutside(isOutSideCancel)
        dialog?.setCancelable(isCancel)
        return inflater.inflate(R.layout.ui_dialog_comm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<AppCompatTextView>(R.id.tv_dialogContent)!!.text = content
        title?.let {
            val tvTitle = view.findViewById<AppCompatTextView>(R.id.tv_dialogTitle)!!
            tvTitle.setVisible(true)
            tvTitle.text = it
        }
        if (btnList.size > 0) {
            val tvBtn = view.findViewById<AppCompatTextView>(R.id.tv_btn0)!!
            tvBtn.text = btnList[0]
            tvBtn.onClick {
                if (onClick(dialog, 0)) {
                    dialog?.dismiss()
                }
            }
        }
        if (btnList.size > 1) {
            val tvBtn = view.findViewById<AppCompatTextView>(R.id.tv_btn1)!!
            tvBtn.setVisible(true)
            tvBtn.text = btnList[1]
            tvBtn.onClick {
                if (onClick(dialog, 1)) {
                    dialog?.dismiss()
                }
            }
        }
        if (btnList.size > 2) {
            val tvBtn = view.findViewById<AppCompatTextView>(R.id.tv_btn2)!!
            tvBtn.setVisible(true)
            tvBtn.text = btnList[2]
            tvBtn.onClick {
                if (onClick(dialog, 2)) {
                    dialog?.dismiss()
                }
            }
        }
    }
}