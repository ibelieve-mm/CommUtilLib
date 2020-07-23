package mm.chenme.lib.commutillibdemo.consts

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/6/21
 * Email：ibelieve1210@163.com
 */
object EventKey {
    const val LoginSuccess = "LoginSuccess"
    const val LogoutSuccess = "LogoutSuccess"
}

/*

// 发送
LiveEventBus.get(EventKey.LoginSuccess).post("")

// 接收
// import androidx.lifecycle.Observer
LiveEventBus.get(EventKey.LoginSuccess, String::class.java).observe(this, Observer {
    // TODO 收到广播后处理事件
})

 */