package mm.chenme.lib.commutillibdemo

import org.junit.Test

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/9/29
 * Email：ibelieve1210@163.com
 */

open class Person {
    var name: String = ""
}

open class Worker : Person() {
    var workLevel: Int = 0
}

class Teacher : Worker() {
    var isMale: Boolean = false
}

class Life<T> {
    var people: T? = null
}

class GenericTest {
    @Test
    fun genericTest() {
        val lifeWorker: Life<Worker> = Life<Worker>()
        val lifePerson: Life<out Person> = lifeWorker
        val lifeTeacher: Life<in Teacher> = lifeWorker
    }
}