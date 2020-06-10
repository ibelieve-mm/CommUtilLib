package mm.chenme.lib.commutillibdemo

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/6/10
 * Email：ibelieve1210@163.com
 */
class Kt(
    private val mAction1: () -> Unit,
    private val mAction2: (String) -> Unit,
    private val mAction3: () -> Boolean,
    private val mAction4: (String) -> Boolean,
    private val mAction5: (String, Int) -> Boolean
) {

    fun funcNoParam(action: () -> Unit) {
        println("执行参数1：")
        mAction1()
        println()

        println("执行参数2：")
        mAction2("参数2")
        println()

        println("执行参数3：${mAction3()}")
        println()

        println("执行参数4：${mAction4("参数4")}")
        println()

        println("执行参数5：${mAction5("参数5", 2)}")
        println()

        action()
    }
}