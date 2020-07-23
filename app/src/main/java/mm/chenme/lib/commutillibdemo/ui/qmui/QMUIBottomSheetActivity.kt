package mm.chenme.lib.commutillibdemo.ui.qmui

import android.view.View
import com.qmuiteam.qmui.kotlin.dip
import com.qmuiteam.qmui.kotlin.onClick
import com.qmuiteam.qmui.widget.dialog.*
import kotlinx.android.synthetic.main.act_qmui_bottom_sheet.*
import kotlinx.android.synthetic.main.act_qmui_bottom_sheet.topbar
import kotlinx.android.synthetic.main.act_tab_layout_divider.*
import mm.chenme.lib.commutillib.BaseActivity
import mm.chenme.lib.commutillib.pro.view.ShapeRippleButton
import mm.chenme.lib.commutillib.utils.color
import mm.chenme.lib.commutillib.utils.stoast
import mm.chenme.lib.commutillibdemo.R
import org.jetbrains.anko.find

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
        initTopBar(topbar,"QMUIBottomSheet")
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

        tv_showDialog.onClick {
//            QMUIDialog.Messag

         val dialog =    BottomSheetBuilder(this,R.layout.dialog_base)
             .setAllowDrag(true)
                .build()
             dialog.show()
            dialog.find<ShapeRippleButton>(R.id.srb_retry).onClick { stoast("qweq“”") }

//            val dialog = QMUIDialog.CustomDialogBuilder(this)
//                .setLayout(R.layout.dialog_base)
//                .create()
//            dialog.find<ShapeRippleButton>(R.id.srb_retry).onClick { stoast("qweq“”我嗯嗯无群二") }
//
//            dialog.show()
        }
    }
}