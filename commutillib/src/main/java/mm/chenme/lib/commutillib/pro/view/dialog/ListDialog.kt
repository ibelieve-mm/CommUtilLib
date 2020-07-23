package mm.chenme.lib.commutillib.pro.view.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.qmuiteam.qmui.kotlin.onClick
import mm.chenme.lib.commutillib.R
import mm.chenme.lib.commutillib.pro.adapter.BaseRecyclerViewAdapter
import mm.chenme.lib.commutillib.utils.setVisible

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/6/14
 * Email：ibelieve1210@163.com
 */


class ListDialog(
    private val itemList: MutableList<String>,
    private val title: String? = null,
    private val isOutSideCancel: Boolean = true,
    private val isCancel: Boolean = true,
    private val onClick: (dialog: Dialog?, index: Int) -> Boolean
) : BaseDialog() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.setCanceledOnTouchOutside(isOutSideCancel)
        dialog?.setCancelable(isCancel)
        return inflater.inflate(R.layout.ui_dialog_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = BaseRecyclerViewAdapter(requireContext(), itemList, R.layout.lt_for_list_dialog) { rootView, dataItetem, pos ->
            (rootView as AppCompatTextView).text = dataItetem
            rootView.onClick {
                if (onClick(dialog, pos)) {
                    dialog?.dismiss()
                }
            }
        }
        title?.let {
            val tvTitle = view.findViewById<AppCompatTextView>(R.id.tv_dialogTitle)!!
            tvTitle.setVisible(true)
            tvTitle.text = it
        }
    }
}