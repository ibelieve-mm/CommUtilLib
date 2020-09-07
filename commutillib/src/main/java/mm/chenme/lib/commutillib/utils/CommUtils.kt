package mm.chenme.lib.commutillib.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.ImageSpan
import android.widget.TextView
import java.lang.ref.WeakReference

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/5/25
 * Email：ibelieve1210@163.com
 */
/**
 * App版本 name
 * @return
 */
val appVersionName get() = AppGlobals.application().packageManager.getPackageInfo(AppGlobals.application().packageName, 0)?.versionName ?: ""

/**
 * App版本 code
 * @return
 */
val appVersionCode get() = AppGlobals.application().packageManager.getPackageInfo(AppGlobals.application().packageName, 0)?.versionCode ?: 1
//val appVersionCodeLong @RequiresApi(Build.VERSION_CODES.P)
//get() = AppGlobals.application().packageManager.getPackageInfo(AppGlobals.application().packageName, 0)?.longVersionCode ?: 1
