package mm.chenme.lib.commutillib.utils

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/6/2
 * Email：ibelieve1210@163.com
 */
private val HEX_DIGITS = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')

fun CharSequence?.isNotNullOrEmpty(): Boolean {
    return !this.isNullOrEmpty()
}

/**
 * 获取指定的字节数组对应的十六进制字符串，按照 ASCII 码表计算
 * 比如 ABCDEFGHIJKLMNOPQRSTUVWXYZ
 * 将得到 4142434445464748494A4B4C4D4E4F505152535455565758595A
 *
 * @param bytes 字节数组
 * @return 十六进制字符串
 */
fun ByteArray?.bytes2HexString(): String {
    if (this == null) return ""
    val len = this.size
    if (len <= 0) return ""
    val ret = CharArray(len shl 1)
    var i = 0
    var j = 0
    while (i < len) {
        // 字节的高八位
        ret[j++] = HEX_DIGITS[this[i].toInt() shr 4 and 0x0f]
        // 字节的低八位
        ret[j++] = HEX_DIGITS[this[i].toInt() and 0x0f]
        i++
    }

    return String(ret)
}

/**
 * 将十六进制字符串转换回字节数组
 *
 * @param hexString 十六进制字符串
 * @return 字节数组
 */
fun String?.hexString2Bytes(): ByteArray {
    if (this.isNullOrEmpty()) return ByteArray(0)
    val len = this.length
    val data = ByteArray(len / 2)
    var i = 0
    while (i < len) {
        data[i / 2] = ((Character.digit(this[i], 16) shl 4) + Character.digit(this[i + 1], 16)).toByte()
        i += 2
    }
    return data
}

fun String?.toIntNoErr(default: Int = 0): Int {
    if (null == this) {
        return default
    }
    return try{
        this.toInt()
    }catch (e:NumberFormatException){
        default
    }
}


