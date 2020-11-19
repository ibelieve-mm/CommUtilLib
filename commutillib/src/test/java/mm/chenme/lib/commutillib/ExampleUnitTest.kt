package mm.chenme.lib.commutillib

import mm.chenme.lib.commutillib.utils.*
import org.junit.Test

import org.junit.Assert.*
import java.nio.charset.StandardCharsets

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun urlDecodeTest() {
        println("ABCDEFGHIJKLMNOPQRSTUVWXYZ".toByteArray().bytes2HexString()) // 4142434445464748494A4B4C4D4E4F505152535455565758595A
        println(String("4142434445464748494A4B4C4D4E4F505152535455565758595A".hexString2Bytes(), StandardCharsets.UTF_8)) // ABCDEFGHIJKLMNOPQRSTUVWXYZ
        println("你好の富士康家福克斯fjskfhjsk123".md2())    // F73B2B7620B27C4C42C1170A899DBD48
        println("你好の富士康家福克斯fjskfhjsk123".md5())    // B98CF799138BE57D59C69BE91A017D81
        println("你好の富士康家福克斯fjskfhjsk123".sha1())   // 72F4E28E398D64BDFBDF0B7062DEE3B717BFA2FC
    }

    @Test
    fun testToInt() {
        println("12".toIntNoErr())
        println("djakl".toIntNoErr())
        println("-1".toIntNoErr())
    }

    @Test
    fun testRandom() {
        println((8 + Math.random() * (14 - 8 + 1)).toInt())
        println((8 + Math.random() * (14 - 8 + 1)).toInt())
        println((8 + Math.random() * (14 - 8 + 1)).toInt())
        println((8 + Math.random() * (14 - 8 + 1)).toInt())
        println((8 + Math.random() * (14 - 8 + 1)).toInt())
        println((8 + Math.random() * (14 - 8 + 1)).toInt())
        println((8 + Math.random() * (14 - 8 + 1)).toInt())
        println((8 + Math.random() * (14 - 8 + 1)).toInt())
        println((8 + Math.random() * (14 - 8 + 1)).toInt())
        println((8 + Math.random() * (14 - 8 + 1)).toInt())
        println((8 + Math.random() * (14 - 8 + 1)).toInt())
        println(randomIntFromAtoB(8, 14))
        println(randomIntFromAtoB(8, 14))
        println(randomIntFromAtoB(8, 14))
        println(randomIntFromAtoB(8, 14))
        println(randomIntFromAtoB(8, 14))
        println(randomIntFromAtoB(8, 14))
        println(randomIntFromAtoB(8, 14))
        println(randomIntFromAtoB(8, 14))
        println(randomIntFromAtoB(8, 14))
        println(randomIntFromAtoB(8, 14))
        println(randomIntFromAtoB(8, 14))
        println(randomIntFromAtoB(8, 14))
        println(randomIntFromAtoB(8, 14))
        println(randomIntFromAtoB(8, 14))
        println(randomIntFromAtoB(8, 14))
        println(randomIntFromAtoB(8, 14))
        println(randomIntFromAtoB(8, 14))
    }

    @Test
    fun hmacSHA256Test() {
//        加密前：{"ids":[5708792],"token":"91d081902f8474cad7ff878cd6cb6914"}4,Android,1.2.0
//        加密后：edd466d6a91264be263c7ac7ed4249d2e8288092835e12f7555ff4ba9fe744e5
//        key：#C5jk._}
        println(hmacSHA256("""{"ids":[5708792],"token":"91d081902f8474cad7ff878cd6cb6914"}4,Android,1.2.0""", "#C5jk._}"))
    }


    data class Per(var id: Int = 0, var name: String = "") {
        override fun equals(other: Any?): Boolean {
            if (other is Per) {
                return this.id == other.id
            }
            return false
        }

//        override fun hashCode(): Int {
//            return this.id.hashCode()
//        }
    }

    @Test
    fun paichong() {
        val list1 = mutableListOf<Per>()
        list1.add(Per(1, "1"))
        list1.add(Per(2, "1"))
        list1.add(Per(3, "2"))
        list1.add(Per(4, "3"))
        list1.add(Per(5, "2"))

        val list2 = mutableListOf<Per>()
        list2.add(Per(1, "2"))
        list2.add(Per(3, "2"))
        list2.add(Per(7, "7"))

        list2.forEach {
            if(!list1.contains(it)){
                list1.add(it)
            }
        }
        list1.forEach { println(it.toString()) }
    }
}
