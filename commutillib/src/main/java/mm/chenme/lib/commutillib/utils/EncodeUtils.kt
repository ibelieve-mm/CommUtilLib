package mm.chenme.lib.commutillib.utils

import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.net.URLEncoder
import android.util.Base64;

/**
 * Descriptions：编码工具类
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/6/2
 * Email：ibelieve1210@163.com
 */
/*---------------------------------- URL --------------------------------------*/
fun String?.urlEncode(charsetName: String = "UTF-8"): String =
    if (this.isNullOrEmpty()) "" else try {
        URLEncoder.encode(this, charsetName)
    } catch (e: UnsupportedEncodingException) {
        throw AssertionError(e)
    }


fun String?.urlDecode(charsetName: String = "UTF-8"): String =
    if (this.isNullOrEmpty()) "" else try {
        URLDecoder.decode(this, charsetName)
    } catch (e: UnsupportedEncodingException) {
        throw AssertionError(e)
    }

/*---------------------------------- Base64 --------------------------------------*/
fun String?.base64Encode(): ByteArray = this?.toByteArray()?.base64Encode() ?: ByteArray(0)
fun ByteArray?.base64Encode(): ByteArray = if (this == null || this.isEmpty()) ByteArray(0) else Base64.encode(this, Base64.NO_WRAP)
fun ByteArray?.base64Encode2String(): String = if (this == null || this.isEmpty()) "" else Base64.encodeToString(this, Base64.NO_WRAP)

fun String?.base64Decode(): ByteArray = if (this.isNullOrEmpty()) ByteArray(0) else Base64.decode(this, Base64.NO_WRAP)
fun ByteArray?.base64Decode(): ByteArray = if (this == null || this.isEmpty()) ByteArray(0) else Base64.decode(this, Base64.NO_WRAP)