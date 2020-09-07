package mm.chenme.lib.commutillibdemo.complex_demo.interview.design_patterns.builder

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/8/20
 * Email：ibelieve1210@163.com
 */
abstract class Builder {

    protected val product = Car()

    abstract fun builderPartA()
    abstract fun builderPartB()
    abstract fun builderPartC()

    fun getResult(): Car {
        return product
    }

}