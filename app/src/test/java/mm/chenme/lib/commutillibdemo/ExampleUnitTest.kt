package mm.chenme.lib.commutillibdemo

import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun xxxTest() {
    }

    val li = arrayListOf(0, 1, 2, 3, 4)
    var aaa: String? = null

    @Test
    fun lopperTest() {
        aaa = "111"
        aaa?.apply tag@{
            li.forEach {
                print("---> $it \t")
                if (it == 2) {
                    return@tag
                }
                print("$it \t")
            }
        }
        println("\ntest func is end!")
    }
// 0 	1
// test func is end!
//你好好开会吧，最好能降低你的学习成本，也好节假日回家玩玩
//        li.forEachIndexed { index, it ->
//            println("判断前--> $index \t$it")
//            if (index == 1) {
//                return
//            }
//            println(" $index \t$it")
//        }
    /*
判断前--> 0 	0
0 	0
判断前--> 1 	1
1 	1
判断前--> 2 	2
     */

//        li.forEachIndexed { index, it ->
//            println("判断前--> $index \t$it")
//            if (index > 1) {
//                return@forEachIndexed
//            }
//            println(" $index \t$it")
//        }
    /*
判断前--> 0 	0
0 	0
判断前--> 1 	1
1 	1
判断前--> 2 	2
判断前--> 3 	3
判断前--> 4 	4
     */
//        li.filterIndexed { index, it ->
//            println("$index \t$it")
//            2==it
//        }
    /*
0 	0
1 	1
2 	2
3 	3
4 	4
         */
//    }
}
