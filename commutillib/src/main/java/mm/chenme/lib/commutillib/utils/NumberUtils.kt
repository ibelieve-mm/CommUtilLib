package mm.chenme.lib.commutillib.utils

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