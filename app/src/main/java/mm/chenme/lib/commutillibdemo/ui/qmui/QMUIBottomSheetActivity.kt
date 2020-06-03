package mm.chenme.lib.commutillibdemo.ui.qmui

import android.view.View
import com.qmuiteam.qmui.kotlin.dip
import com.qmuiteam.qmui.kotlin.onClick
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet
import kotlinx.android.synthetic.main.act_qmui_bottom_sheet.*
import mm.chenme.lib.commutillib.BaseActivity
import mm.chenme.lib.commutillib.utils.color
import mm.chenme.lib.commutillib.utils.stoast
import mm.chenme.lib.commutillibdemo.R

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/6/2
 * Email：ibelieve1210@163.com
 */

class QMUIBottomSheetActivity(override val layoutResId: Int = R.layout.act_qmui_bottom_sheet) : BaseActivity() {

    override fun initView() {
        topbar.setTitle("QMUIBottomSheet")
        topbar.addLeftBackImageButton().onClick { closePage() }
    }

    override fun initListener() {
        tv_showGrid.onClick {
            val sheet = QMUIBottomSheet.BottomGridSheetBuilder(this)
                .setAllowDrag(true)
                .setCancelText("cancel")
                .setAddCancelBtn(true)
                .setOnSheetItemClickListener { dialog, itemView ->
                    stoast(itemView.tag as String)
                    dialog.dismiss()
                }
                .addItem(android.R.drawable.alert_dark_frame, "1231312131", QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(android.R.drawable.stat_notify_call_mute, "12", QMUIBottomSheet.BottomGridSheetBuilder.SECOND_LINE)
                .addItem(android.R.drawable.ic_input_add, "23", QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(android.R.drawable.edit_text, "34", QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(android.R.drawable.edit_text, "34", QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(android.R.drawable.edit_text, "44", QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(android.R.drawable.edit_text, "fsfd", QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .build()
            sheet.setRadius(dip(20))
            sheet.show()
        }

    }


}