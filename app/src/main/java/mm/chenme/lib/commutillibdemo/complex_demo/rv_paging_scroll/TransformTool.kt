package mm.chenme.lib.commutillibdemo.complex_demo.rv_paging_scroll

/**
 * Descriptions：
 * StartVersion：
 * <p>
 * Author：ChenME
 * Date：2020/8/4
 * Email：ibelieve1210@163.com
 */

/**
 * 将列表的 m*n 转换成 n*m
 */
fun <T> transformAndFillEmptyData(srcList: MutableList<T>, row: Int, column: Int): MutableList<T?> {

    //1. new a empty ArrayList
    val destList = mutableListOf<T?>()
    val size = srcList.size
    val pageCount = row * column

    //2. get the traverseCount
    val traverseCount = when {
        size < pageCount -> pageCount
        size % pageCount == 0 -> size
        else -> (size / pageCount + 1) * pageCount
    }

    //3. travrse the list
    for (i in 0 until traverseCount) {
        val pre = i / pageCount
        val divisor = i % pageCount
        var index = if (divisor % 2 == 0) { //even
            divisor / 2
        } else { //odd
            column + divisor / 2
        }

        //this is very important
        index += pre * pageCount
        if (index in 0 until size) {
            destList.add(srcList[index])
        } else {
            destList.add(null)
        }
    }

    //4. back
    return destList
}