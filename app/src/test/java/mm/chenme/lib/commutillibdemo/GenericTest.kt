package mm.chenme.lib.commutillibdemo

import org.junit.Test
import java.util.*

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

class ASD {
    var asd: String? = null
    var ccc: String? = null

    @Test
    fun asdddd() {
        asd?.apply {
            println("ASD")
        } ?: apply {
            println("1111111")
        }

//        asd = ccc ?: DatabaseOpenHelper()

        var asdddd = when (asd) {
            null -> ""
            else -> "1233213"
        }
    }

    @Test
    fun mapTest(){
        val a = hashMapOf(Pair("1", false), Pair("2", false), Pair("3", false))
        println(a)
        a["4"] = true
        println(a)
        a.remove("5")
        println(a)
        a.remove("3")
        println(a)


        val keys: Array<String> = a.keys.toTypedArray()
        val random = Random()
        val randomKey = keys[random.nextInt(keys.size)]
        println(keys[random.nextInt(keys.size)])
        println(keys[random.nextInt(keys.size)])
        println(keys[random.nextInt(keys.size)])
        println(keys[random.nextInt(keys.size)])

        val aa ="pc:sLEBap_rT6WUh6qwT8sd0w:nG6nv0bMR6WfkDZJwdcxaA"
        println(aa.contains("sLEBap_rT6WUh6qwT8sd0w"))

    }
}