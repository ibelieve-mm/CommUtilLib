package mm.chenme.lib.commutillibdemo.complex_demo.interview.design_patterns.singleton.kt

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/8/19
 * Email：ibelieve1210@163.com
 */
class SingletonInstance private constructor() {

    companion object {
        val instance = SingletonHolder.holder
    }

    private object SingletonHolder {
        val holder = SingletonInstance()
    }
}

///**
// * 枚举方式
// */
//enum class SingletonInstance {
//    instance;
//
//    fun doSomething() {}
//}
