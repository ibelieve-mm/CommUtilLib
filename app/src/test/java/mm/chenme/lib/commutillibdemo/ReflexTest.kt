package mm.chenme.lib.commutillibdemo

import org.junit.Test
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ReflexTest {

    @Test
    fun reflexTest() {
        val b = B()
        b.reflexVariable()
        b.reflexMethod()
    }


    class B : A() {
        fun reflexVariable() {
            val clz = A::class.java
            val field = clz.getDeclaredField("v1")
            field.isAccessible = true
            println(field.get(this) as Int)

            val field2 = clz.getDeclaredField("v2")
            field2.isAccessible = true
            println(field2.get(this) as String)
            field2.set(this, "Hello reflex!\n")
            println(field2.get(this) as String)
        }

        fun reflexMethod() {
            val clz = A::class.java

            /**
             * 静态无参数函数反射
             * invoke 函数参数1可传的的数据类型为：
             * ① clz 的实例；
             * ② clz 子类的实例；
             * ③ null
             */
            val method = clz.getDeclaredMethod("printStatic")
            method.isAccessible = true
            println(method.invoke(null) as String)

            /**
             * 普通无参数函数反射
             * invoke 函数参数1可传的的数据类型为：
             * ① clz 的实例；
             * ② clz 子类的实例；
             */
            val method1 = clz.getDeclaredMethod("printNoParam")
            method1.isAccessible = true
            println(method1.invoke(this) as String)

            /**
             * 带有参数的函数反射
             */
            val method2 = clz.getDeclaredMethod("printHasParams", String::class.java, Int::class.java)
            method2.isAccessible = true
            println(method2.invoke(this, "arg1", 100) as String)
        }
    }

    open class A {
        private val v1 = 100
        private val v2 = "reflex"

        companion object {
            @JvmStatic
            private fun printStatic(): String {
                println("Hello reflex in static!")
                return "execute complete!\n"
            }
        }

        private fun printNoParam(): String {
            println("Hello reflex without param!")
            return "execute complete!\n"
        }

        private fun printHasParams(param1: String, param2: Int): String {
            println("Hello reflex with params. param1:$param1, param2:$param2")
            return "execute complete!\n"
        }
    }
}
