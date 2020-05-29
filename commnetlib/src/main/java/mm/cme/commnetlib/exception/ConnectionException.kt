package mm.cme.commnetlib.exception

import mm.cme.commnetlib.config.Err_ConnectFail

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/5/28
 * Email：ibelieve1210@163.com
 */
class ConnectionException : BaseException(Err_ConnectFail, "网络请求失败")
