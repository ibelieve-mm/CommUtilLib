package mm.chenme.lib.commutillib.utils

import java.util.*

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/8/21
 * Email：ibelieve1210@163.com
 */

/**
 * 生成 [a, b] 的一个随机整数
 */
fun randomIntFromAtoB(a: Int, b: Int): Int {
    return (a + Math.random() * (b - a + 1)).toInt()
}

/**
 * 四舍五入保留 n 位小数
 * 强制使用英文样式，避免多语言造成的混乱
 */
fun Float?.keepDecimals(n: Int = 1, default: String = "0.0"): String {
    if (null == this) {
        return default
    }
    return String.format(Locale.ENGLISH, "%.${n}f", this)
}