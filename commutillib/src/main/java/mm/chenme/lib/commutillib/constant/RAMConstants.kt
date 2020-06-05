package mm.chenme.lib.commutillib.constant

import androidx.annotation.LongDef

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/6/4
 * Email：ibelieve1210@163.com
 */
object RAMConstants {
    const val B = 1L
    const val MB = 1_048_576L
    const val MB256 = 268_435_456L
    const val GB = 1_073_741_824L

    @LongDef(B, MB, MB256, GB)
    @Retention(AnnotationRetention.SOURCE)
    annotation class RAMUnit
}