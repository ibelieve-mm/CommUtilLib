package mm.cme.commnetlib.exception

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/5/28
 * Email：ibelieve1210@163.com
 */

open class BaseException(private var errorCode: Int, message: String?) : RuntimeException()