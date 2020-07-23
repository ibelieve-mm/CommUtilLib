package mm.chenme.lib.commutillib.pro.view.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import androidx.fragment.app.DialogFragment
import com.qmuiteam.qmui.kotlin.dip

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/6/14
 * Email：ibelieve1210@163.com
 */
abstract class BaseDialog : DialogFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 去掉边距，高度自适应
        val dm = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(dm)
        dialog!!.window!!.setLayout(dm.widthPixels - dip(80), dialog!!.window!!.attributes.height)

        // 去掉背景色
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}