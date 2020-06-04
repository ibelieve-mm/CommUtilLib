package mm.chenme.lib.commutillibdemo

import org.junit.Test
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class KotlinSpecialFuncTest {

    data class User(
        var name: String = "",
        var nickName: String = "",
        var gender: String = "",
        var age: Int = 18
    )

    @Test
    fun ktWithTest() {

        println(with(User()) {
            println(this)
            name = "小麦"
            nickName = "麦子"
            gender = "f"
            age = 18
        })

//        with(mutableListOf<Int>()) {
//            add(1)
//            add(2)
//            add(3)
//            println(this)
//        }
    }

    @Test
    fun ktLetTest() {

        println(User().let {
            println(this)
            it.name = "小麦"
            it.nickName = "麦子"
            it.gender = "f"
            it.age = 18
        })
//        println("asd".let {
//            println(it)
//            1 + 1
//        })
    }

    @Test
    fun ktRunTest() {
        println(User().run {
            println(this)
            name = "小麦"
            nickName = "麦子"
            gender = "f"
            age = 18
        })
//        println(mutableListOf<Int>().run {
//            add(101)
//            add(102)
//            add(103)
//            add(104)
//            println(this)
//            Date()
//        })
    }

    @Test
    fun ktApplyTest() {
        println(User().apply {
            println(this)
            name = "小麦"
            nickName = "麦子"
            gender = "f"
            age = 18
        })
//        println(mutableListOf<Int>().apply {
//            add(101)
//            add(102)
//            add(103)
//            add(104)
//        })
    }

    @Test
    fun ktAlsoTest() {
        println(User().also {
            println(this)
            it.name = "小麦"
            it.nickName = "麦子"
            it.gender = "f"
            it.age = 18
        })
//        val also = Date().also {
//            println("in also time = " + it.time)
//        }
//        println("also = $also")
    }

    @Test
    fun ktTakeIfTest() {
        val date = Date().takeIf {
            // 是否在2018年元旦后
            val data2 = Date(2018 - 1900, 0, 1)
            println(data2)
            val result = it.after(data2)
            println(result)
            result
        }
        println("date = $date")
    }

    @Test
    fun ktTakeUnlessTest() {
        val date = Date().takeUnless {
            // 是否在2018年元旦后
            val data2 = Date(2018 - 1900, 0, 1)
            println(data2)
            val result = it.after(data2)
            println(result)
            result
        }
        println("date = $date")
    }


    @Test
    fun ktRepeatTest() {
        repeat(3) {
            println(it)
        }
    }
}
