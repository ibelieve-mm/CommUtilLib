package mm.chenme.lib.commutillib.utils

import android.app.Dialog
import androidx.fragment.app.FragmentManager
import mm.chenme.lib.commutillib.R
import mm.chenme.lib.commutillib.pro.view.dialog.CommDialog
import mm.chenme.lib.commutillib.pro.view.dialog.ListDialog

/**
 * Descriptions：对话框封装
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/6/14
 * Email：ibelieve1210@163.com
 */

fun FragmentManager.alertDialog(
    content: String,
    btn0: String = string(R.string.confirm),
    title: String? = null,
    isOutSideCancel: Boolean = true,
    isCancel: Boolean = true,
    onClick: (dialog: Dialog?, index: Int) -> Boolean
) {
    CommDialog(content, mutableListOf(btn0), title, isOutSideCancel, isCancel, onClick).show(this, "")
}

fun FragmentManager.confirmDialog(
    content: String,
    btn0: String = string(R.string.confirm),
    btn1: String = string(R.string.cancel),
    title: String? = null,
    isOutSideCancel: Boolean = true,
    isCancel: Boolean = true,
    onClick: (dialog: Dialog?, index: Int) -> Boolean
) {
    CommDialog(content, mutableListOf(btn0, btn1), title, isOutSideCancel, isCancel, onClick).show(this, "")
}

fun FragmentManager.threeDialog(
    content: String,
    btn0: String = string(R.string.confirm),
    btn1: String = string(R.string.cancel),
    btn3: String = "",
    title: String? = null,
    isOutSideCancel: Boolean = true,
    isCancel: Boolean = true,
    onClick: (dialog: Dialog?, index: Int) -> Boolean
) {
    CommDialog(content, mutableListOf(btn0, btn1, btn3), title, isOutSideCancel, isCancel, onClick).show(this, "")
}

fun FragmentManager.listDialog(
    itemList: MutableList<String>,
    title: String? = null,
    isOutSideCancel: Boolean = true,
    isCancel: Boolean = true,
    onClick: (dialog: Dialog?, index: Int) -> Boolean
) {
    ListDialog(itemList, title, isOutSideCancel, isCancel, onClick).show(this, "")
}

/*

// 在 Fragment 中使用
requireActivity().supportFragmentManager.alertDialog(string(R.string.callServicePhone)) { _, _ ->
    mActivity.callPhone("10010")
    return@alertDialog true
}

// 在 Activity 中使用
supportFragmentManager.confirmDialog("确认删除「文章$data」？") { dialog, index ->
    when (index) {
        0 -> stoast("确认")
        1 -> stoast("取消")
    }
    return@confirmDialog true
}

supportFragmentManager.listDialog(mutableListOf("1个", "2个", "3个", "4个", "5个", "6个", "7个", "8个", "9个")) { _, index ->
    updateUI(index + 1)
    return@listDialog true
}
 */
