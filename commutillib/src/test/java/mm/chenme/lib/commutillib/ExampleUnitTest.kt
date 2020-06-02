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
}
