package mm.chenme.lib.commutillibdemo.ui.qmui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheetBaseBuilder
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheetRootLayout

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/6/12
 * Email：ibelieve1210@163.com
 */
class BottomSheetBuilder(private val mContext: Context, @LayoutRes val mLayoutResId: Int) : QMUIBottomSheetBaseBuilder<BottomSheetBuilder>(mContext) {
    private  var mView :View?=null
    override fun onCreateContentView(bottomSheet: QMUIBottomSheet, rootLayout: QMUIBottomSheetRootLayout, context: Context): View? {

        mView = LayoutInflater.from(context).inflate(mLayoutResId, rootLayout, false)
        return mView
    }
}