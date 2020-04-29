package mm.chenme.lib.commutillib.exts

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020-04-29
 * Email：ibelieve1210@163.com
 */
val YMDHMSDot = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.CHINA)

val YMDHMS = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)
val YMDHM = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA)
val YMD = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
val YearChina = SimpleDateFormat("yyyy年", Locale.CHINA)
val HMS = SimpleDateFormat("HH:mm:ss", Locale.CHINA)

val YMDHMS_Short = SimpleDateFormat("yy-M-d HH:mm:ss", Locale.CHINA)
val YMDHM_Short = SimpleDateFormat("yy-M-d HH:mm", Locale.CHINA)
val YMD_Short = SimpleDateFormat("yy-M-d", Locale.CHINA)


fun Date.format(dateFormat: DateFormat): String = dateFormat.format(this)

fun Date?.ymdhmsDot(default: String = ""): String = this?.format(YMDHMSDot) ?: default
fun Date?.ymdhms(default: String = ""): String = this?.format(YMDHMS) ?: default
fun Date?.ymdhm(default: String = ""): String = this?.format(YMDHM) ?: default
fun Date?.ymd(default: String = ""): String = this?.format(YMD) ?: default
fun Date?.yearChina(default: String = ""): String = this?.format(YearChina) ?: default
fun Date?.hms(default: String = ""): String = this?.format(HMS) ?: default

fun Date?.ymdhmsShort(default: String = ""): String = this?.format(YMDHMS_Short) ?: default
fun Date?.ymdhmShort(default: String = ""): String = this?.format(YMDHM_Short) ?: default
fun Date?.ymdShort(default: String = ""): String = this?.format(YMD_Short) ?: default